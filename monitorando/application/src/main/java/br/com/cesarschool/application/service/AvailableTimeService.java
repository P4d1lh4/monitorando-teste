package br.com.cesarschool.application.service;

import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.repository.user.AvaliableTimeRepository;
import br.com.cesarschool.domain.repository.user.FindMonitorRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Service
public class AvailableTimeService {

    private final AvaliableTimeRepository availableTimeRepository;
    private final FindMonitorRepository<MonitorEntity> findMonitorRepository;

    public AvailableTimeService(
            AvaliableTimeRepository availableTimeRepository,
            FindMonitorRepository<MonitorEntity> findMonitorRepository
    ) {
        this.availableTimeRepository = availableTimeRepository;
        this.findMonitorRepository = findMonitorRepository;
    }

    public AvailableTimeEntity defineAvailableTime(AvailableTimeEntity newTime, Long monitorId) {
        // Validações de horário
        if (newTime.getStartTime().isAfter(newTime.getEndTime()) ||
                newTime.getStartTime().equals(newTime.getEndTime())) {
            throw new IllegalArgumentException("Horário inválido: o horário de início deve ser antes do término.");
        }

        LocalTime inicioPermitido = LocalTime.of(8, 0);
        LocalTime fimPermitido = LocalTime.of(22, 0);

        if (newTime.getStartTime().isBefore(inicioPermitido) ||
                newTime.getEndTime().isAfter(fimPermitido)) {
            throw new IllegalArgumentException("Horário inválido: deve estar entre 08:00 e 22:00.");
        }

        Duration duracao = Duration.between(newTime.getStartTime(), newTime.getEndTime());

        if (duracao.toMinutes() < 30) {
            throw new IllegalArgumentException("Horário inválido: duração mínima de 30 minutos.");
        }

        if (duracao.toHours() > 2 || (duracao.toHours() == 2 && duracao.toMinutesPart() > 0)) {
            throw new IllegalArgumentException("Horário inválido: duração máxima de 2 horas.");
        }

        // Conflito com horários já existentes
        List<AvailableTimeEntity> existingTimes = availableTimeRepository.findByMonitorId(monitorId);

        boolean hasConflict = existingTimes.stream().anyMatch(existing ->
                existing.getWeekDay() == newTime.getWeekDay() &&
                        !(newTime.getEndTime().isBefore(existing.getStartTime()) ||
                                newTime.getStartTime().isAfter(existing.getEndTime()))
        );

        if (hasConflict) {
            throw new IllegalArgumentException("Horário conflitante com outro já existente.");
        }

        // Salva o horário
        AvailableTimeEntity saved = availableTimeRepository.save(newTime, monitorId);

        // Recupera o monitor e simula notificação
        findMonitorRepository.findById(monitorId).ifPresent(monitor -> {
            System.out.println("Notificando alunos da disciplina: " + monitor.getDisciplineId());
            System.out.println("Horários atualizados:");
            availableTimeRepository.findByMonitorId(monitorId).forEach(horario -> {
                System.out.println("- " + horario.getWeekDay() + ": " +
                        horario.getStartTime() + " até " + horario.getEndTime());
            });
        });

        return saved;
    }

    public void removeAvailableTimeById(Long id) {
        availableTimeRepository.deleteById(id);
    }

    public List<AvailableTimeEntity> getAvailableTimesByMonitor(Long monitorId) {
        return availableTimeRepository.findByMonitorId(monitorId);
    }
}
