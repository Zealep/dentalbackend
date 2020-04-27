package com.zealep.dental.app.model.repository.jdbc;

import com.zealep.dental.app.model.dto.CitaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class CitaJdbcRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<CitaDTO> obtenerCitasPorFecha(LocalDate fecha){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("fecha",fecha);
        
        String sql = "select c.id_cita AS id_cita," +
                "p.apellidos AS apellidos_paciente," +
                "p.nombres AS nombres_paciente," +
                "d.apellidos AS apellidos_doctor," +
                "d.nombres AS nombres_doctor," +
                "c.fecha_hora AS fecha_hora," +
                "c.asunto AS asunto," +
                "c.etapa AS etapa " +
                "from cita c " +
                "inner join paciente p on p.id_paciente = c.id_paciente " +
                "inner join doctor d on d.id_doctor = c.id_doctor " +
                "where convert(c.fecha_hora,date) = convert(:fecha,date)"+
                "and c.estado='A'";
        return (List<CitaDTO>) jdbcTemplate.query(sql, parameters,new CitaDTOMapper());
    }


        private static final class CitaDTOMapper implements RowMapper<CitaDTO> {

        @Override
        public CitaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            CitaDTO c = new CitaDTO();
            c.setIdCita(rs.getLong("id_cita"));
            c.setApellidosPaciente(rs.getString("apellidos_paciente"));
            c.setNombresPaciente(rs.getString("nombres_paciente"));
            c.setApellidosDoctor(rs.getString("apellidos_doctor"));
            c.setNombresDoctor(rs.getString("nombres_doctor"));
            c.setFechaHora(rs.getObject("fecha_hora", LocalDateTime.class));
            c.setAsunto(rs.getString("asunto"));
            c.setEtapa(rs.getString("etapa"));
            return c;

        }
    }


}
