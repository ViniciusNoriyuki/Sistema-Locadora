package br.com.locadora.filmes.filme.facade;

import br.com.locadora.filmes.filme.mapper.FilmeMapper;
import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.locadora.filmes.filme.model.FilmeEntrada;
import br.com.locadora.filmes.filme.model.FilmeSaida;
import br.com.locadora.filmes.filme.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeFacade {

    @Autowired
    FilmeRepository filmeRepository;

    public FilmeSaida salvarFilme(FilmeEntrada filmeEntrada) throws Exception {

        validarDadosEntrada(filmeEntrada);

        FilmeEntity filmeEntity = FilmeMapper.INSTANCE.converterEntradaParaEntity(filmeEntrada);
        filmeEntity.setStatusAluguel("Disponivel");

        filmeEntity = filmeRepository.save(filmeEntity);

        return FilmeMapper.INSTANCE.converterEntityParaSaida(filmeEntity);
    }

    public void validarDadosEntrada(FilmeEntrada filmeEntrada) throws Exception {
        if (filmeEntrada.getNome() == null || filmeEntrada.getGenero() == null || filmeEntrada.getDataLancamento() == null ||
                filmeEntrada.getClassificacaoIndicativa() == null || filmeEntrada.getValorFilme() == null ||
                filmeEntrada.getNome().isEmpty() || filmeEntrada.getGenero().isEmpty() || filmeEntrada.getClassificacaoIndicativa() < 0 ||
                filmeEntrada.getValorFilme() < 0)
        {
            throw new Exception("Dado invalido para entrada");
        }
    }

    public List<FilmeSaida> listarFilmes() throws Exception {

        List<FilmeEntity> listaFilmeEntity = filmeRepository.findAll();

        if (listaFilmeEntity.isEmpty()){
            throw new Exception("Nao ha filmes no banco de dados");
        }

        return FilmeMapper.INSTANCE.converterListEntityParaListSaida(listaFilmeEntity);
    }

    public FilmeSaida alterarValorFilme(Long idFilme , FilmeEntrada entrada) throws Exception {

        FilmeEntity entidade;
        Optional<FilmeEntity> retornoBanco = filmeRepository.findById(idFilme);
        if (!retornoBanco.isPresent()){
            throw new Exception("Filme nao encontrado");
        }

        entidade = retornoBanco.get();

        if (entrada.getValorFilme() == null || entrada.getValorFilme() < 0 || entrada.getValorFilme().equals(entidade.getValorFilme())){
            throw new Exception("Dado invalido para alterar valor do filme");
        }

        entidade.setValorFilme(entrada.getValorFilme());

        entidade = filmeRepository.save(entidade);

        return FilmeMapper.INSTANCE.converterEntityParaSaida(entidade);
    }

    public FilmeSaida buscarFilme(Long idFilme) throws Exception {
        FilmeEntity entidade;

        Optional<FilmeEntity> retornoBanco = filmeRepository.findById(idFilme);
        if (!retornoBanco.isPresent()){
            throw new Exception("Filme nao encontrado");
        }

        entidade = retornoBanco.get();

        return FilmeMapper.INSTANCE.converterEntityParaSaida(entidade);
    }
}
