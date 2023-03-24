package com.zealep.dental.app.model.repository.jdbc;

import com.zealep.dental.app.model.dto.TratamientoPagarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class TratamientoJdbcRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<TratamientoPagarDTO> obtenerPagarPorTratamiento(Long idPaciente){

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idPaciente",idPaciente);

        String sql = "select t.id_tratamiento,t.nombre AS nombre_tratamiento," +
                "d.apellidos AS apellidos_doctor," +
                "d.nombres AS nombres_doctor," +
                "p.apellidos AS apellidos_paciente," +
                "p.nombres AS nombres_paciente," +
                "p.dni AS dni_paciente," +
                "t.monto_total AS total_tratamiento," +
                "(CASE WHEN pago_total is null then 0 else pago_total END) as pago_total  " +
                "from (select * from tratamiento where id_paciente = :idPaciente) t " +
                "left join paciente p on t.id_paciente = t.id_paciente " +
                "left join doctor d on t.id_doctor = d.id_doctor " +
                "left join (select p.id_tratamiento,SUM(p.monto)as pago_total from pago p where estado='A' group by p.id_tratamiento)  pa  on t.id_tratamiento = pa.id_tratamiento " +
                "where p.id_paciente = :idPaciente";

        return (List<TratamientoPagarDTO>)jdbcTemplate.query(sql, parameters, new TratatamientoPagarDTOMapper());
    }



     private static final class TratatamientoPagarDTOMapper implements RowMapper<TratamientoPagarDTO> {

        @Override
        public TratamientoPagarDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            TratamientoPagarDTO t = new TratamientoPagarDTO();
            t.setIdTratamiento(rs.getLong("id_tratamiento"));
            t.setNombreTratamiento(rs.getString("nombre_tratamiento"));
            t.setApellidosDoctor(rs.getString("apellidos_doctor"));
            t.setNombresDoctor(rs.getString("nombres_doctor"));
            t.setApellidosPaciente(rs.getString("apellidos_paciente"));
            t.setNombresPaciente(rs.getString("nombres_paciente"));
            t.setDocumentoIdentidadPaciente(rs.getString("dni_paciente"));
            t.setMontoTotalTratamiento(rs.getDouble("total_tratamiento"));
            t.setMontoTotalPagado(rs.getDouble("pago_total"));
            return t;

        }
    }


}

