package br.com.locadora.filmes.aluguel.facade;

import br.com.locadora.filmes.aluguel.mapper.AluguelMapper;
import br.com.locadora.filmes.aluguel.model.AluguelEntity;
import br.com.locadora.filmes.aluguel.model.AluguelEntrada;
import br.com.locadora.filmes.aluguel.model.AluguelSaida;
import br.com.locadora.filmes.aluguel.repository.AluguelRepository;
import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.locadora.filmes.filme.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AluguelFacade {

    @Autowired
    FilmeRepository filmeRepository;
    @Autowired
    AluguelRepository aluguelRepository;

    public AluguelSaida alugarFilme(Long idCliente, AluguelEntrada aluguelEntrada) throws Exception {
        List<FilmeEntity> listaFilmeEntity = preencherListaFilmes(aluguelEntrada);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(aluguelEntrada.getDataAluguel());
        calendar.add(Calendar.DATE, +7);
        Date dataDevolucao = calendar.getTime();

        Double valorTotal = 0D;
        for (FilmeEntity filmeEntity : listaFilmeEntity) {
            valorTotal = valorTotal + filmeEntity.getValorFilme();
        }

        String statusDevolucao = "Nao devolvido";

        AluguelEntity aluguelEntity = AluguelMapper.INSTANCE.mapToEntity(idCliente, listaFilmeEntity, aluguelEntrada.getDataAluguel(), dataDevolucao, statusDevolucao, valorTotal);

        aluguelRepository.save(aluguelEntity);

        AluguelSaida saida = AluguelMapper.INSTANCE.converterEntityParaSaida(aluguelEntity);

        return saida;
    }

    public List<AluguelSaida> extratoAluguelPorPeriodo(Date dateFrom, Date dateTo) throws Exception {
        List<AluguelEntity> listaTransacoesEntity = aluguelRepository.findAll();

        List<AluguelSaida> listaTransacoesSaida = new ArrayList<>();

        for (AluguelEntity transacaoEntity : listaTransacoesEntity) {
            if (dateFrom.compareTo(transacaoEntity.getDataAluguel()) <= 0 && dateTo.compareTo(transacaoEntity.getDataAluguel()) >= 0) {
                listaTransacoesSaida.add(AluguelMapper.INSTANCE.converterEntityParaSaida(transacaoEntity));
            }
        }

        if (listaTransacoesSaida.isEmpty()) {
            throw new Exception("Nao ha filmes alugados no periodo informado");
        }

        return listaTransacoesSaida;
    }

    public AluguelSaida devolverFilme(Long idAluguel, Long idCliente) throws Exception {

        Optional<AluguelEntity> retornoBanco = aluguelRepository.findById(idAluguel);
        if (!retornoBanco.isPresent()){
            throw new Exception("Aluguel nao encontrado");
        }

        AluguelEntity entidade = retornoBanco.get();

        List<FilmeEntity> listaFilmes = new ArrayList<>();

        if (entidade.getIdCliente().equals(idCliente)){
            if (entidade.getStatusDevolucao().equals("Devolvido")){
                throw new Exception("Este cliente ja devolveu os filmes");
            }
            entidade.setStatusDevolucao("Devolvido");
            for (FilmeEntity filmeEntity : entidade.getListaFilmes()){
                filmeEntity.setStatusAluguel("Disponivel");
                filmeEntity = filmeRepository.save(filmeEntity);
                listaFilmes.add(filmeEntity);
            }
        } else {
            throw new Exception("Cliente nao eh o mesmo para este aluguel");
        }

        entidade = aluguelRepository.save(entidade);

        return AluguelMapper.INSTANCE.converterEntityParaSaida(entidade);
    }

    public List<AluguelSaida> relatorioAlugueisVencidosENaoDevolvidos(Date dateFrom) throws Exception {
        List<AluguelEntity> listaTransacoesEntity = aluguelRepository.findAll();

        List<AluguelSaida> listaTransacoesSaida = new ArrayList<>();

        for (AluguelEntity transacaoEntity : listaTransacoesEntity) {
            if (dateFrom.compareTo(transacaoEntity.getDataDevolucao()) > 0 && transacaoEntity.getStatusDevolucao().equals("Nao devolvido")) {
                listaTransacoesSaida.add(AluguelMapper.INSTANCE.converterEntityParaSaida(transacaoEntity));
            }
        }

        if (listaTransacoesSaida.isEmpty()) {
            throw new Exception("Nao ha filmes com aluguel vencido no periodo informado");
        }

        return listaTransacoesSaida;
    }

    public List<AluguelSaida> listarTodosAlugueis() throws Exception {
        List<AluguelEntity> listaTransacoesEntity = aluguelRepository.findAll();

        if (listaTransacoesEntity.isEmpty()) {
            throw new Exception("Nao ha alugueis realizados");
        }

        return AluguelMapper.INSTANCE.converterListEntityParaListSaida(listaTransacoesEntity);
    }

    public AluguelSaida buscarAluguel(Long idAluguel) throws Exception {
        AluguelEntity entidade;

        Optional<AluguelEntity> retornoBanco = aluguelRepository.findById(idAluguel);
        if (!retornoBanco.isPresent()){
            throw new Exception("Aluguel nao encontrado");
        }

        entidade = retornoBanco.get();

        return AluguelMapper.INSTANCE.converterEntityParaSaida(entidade);
    }

    public List<FilmeEntity> preencherListaFilmes(AluguelEntrada aluguelEntrada) throws Exception {
        List<FilmeEntity> listaFilmes = new ArrayList<>();

        for (Long idFilme : aluguelEntrada.getListaFilmes()){
            FilmeEntity filmeEntity = retornarFilmeEntity(idFilme);
            if (filmeEntity.getStatusAluguel().equals("Alugado")){
                throw new Exception("Filme ja se encontra alugado");
            }
            filmeEntity.setStatusAluguel("Alugado");
            listaFilmes.add(filmeEntity);
        }

        return listaFilmes;
    }

    public FilmeEntity retornarFilmeEntity(Long idFilme) throws Exception {
        FilmeEntity entidade;
        Optional<FilmeEntity> retornoBanco = filmeRepository.findById(idFilme);

        if (!retornoBanco.isPresent()) {
            throw new Exception("Filme nao encontrado!");
        }
        entidade = retornoBanco.get();

        return entidade;
    }
}
