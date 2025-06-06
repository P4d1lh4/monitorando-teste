package br.com.cesarschool.infrastructure.adapter.discipline;

import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.repository.discipline.FindDisciplineRepository;
import br.com.cesarschool.infrastructure.persistence.entity.DisciplineJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.DisciplineMapper;
import br.com.cesarschool.infrastructure.repository.DisciplineJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindDisciplineAdapter implements FindDisciplineRepository {
    private final DisciplineJpaRepository disciplineJpaRepository;

    public FindDisciplineAdapter(DisciplineJpaRepository disciplineJpaRepository) {
        this.disciplineJpaRepository = disciplineJpaRepository;
    }

    @Override
    public Optional<DisciplineEntity> findById(Long id) {
        Optional<DisciplineJpaEntity> disciplineOptional = disciplineJpaRepository.findById(id);

        if (disciplineOptional.isPresent()) {
            DisciplineJpaEntity discipline = disciplineOptional.get();
            return Optional.of(DisciplineMapper.toDomain(discipline));
        }

        return Optional.empty();
    }

    @Override
    public Optional<DisciplineEntity> findByCode(String code) {
        Optional<DisciplineJpaEntity> disciplineOptional = disciplineJpaRepository.findByCode(code);

        if (disciplineOptional.isPresent()) {
            DisciplineJpaEntity discipline = disciplineOptional.get();
            return Optional.of(DisciplineMapper.toDomain(discipline));
        }
        return Optional.empty();
    }

    @Override
    public List<DisciplineEntity> findByStudentId(Long id) {
        return DisciplineMapper.toDomainList(disciplineJpaRepository.findByStudents_Id(id));
    }
}
