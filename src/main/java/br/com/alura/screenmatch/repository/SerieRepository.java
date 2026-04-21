package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(Integer totalTemporadas, Double avaliacao);

    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(@Param("totalTemporadas") Integer totalTemporadas, @Param("avaliacao") Double avaliacao);

    @Query("SELECT e FROM Serie s join s.episodios e WHERE e.titulo ILIKE %:trecho%")
    List<Episodio> episodiosPorTrecho(@Param("trecho") String trecho);

    @Query("SELECT e FROM Serie s join s.episodios e WHERE s = :serie order by s.avaliacao desc limit 5")
    List<Episodio> topEpisodiosPorSerie(@Param("serie") Serie serie);

    @Query("SELECT e FROM Serie s join s.episodios e WHERE s = :serie and Year(e.dataLancamento) >= :dataLancamento")
    List<Episodio> episodiosPorData(@Param("serie") Serie serie, @Param("dataLancamento") int dataLancamento);
}
