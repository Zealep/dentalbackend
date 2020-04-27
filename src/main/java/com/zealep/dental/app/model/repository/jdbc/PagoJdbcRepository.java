package com.zealep.dental.app.model.repository.jdbc;

import com.zealep.dental.app.model.dto.PagoTotalMesesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PagoJdbcRepository {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public List<PagoTotalMesesDTO> obtenerPagoMesesTotal(){
        String sql = "select " +
                "CASE WHEN p.mes = 1 THEN CONCAT('ENERO','-',p.anho) " +
                "WHEN p.mes =2 THEN CONCAT('FEBRERO','-',p.anho) " +
                "WHEN p.mes =3 THEN CONCAT('MARZO','-',p.anho) " +
                "WHEN p.mes =4 THEN CONCAT('ABRIL','-',p.anho) " +
                "WHEN p.mes =5 THEN CONCAT('MAYO','-',p.anho) " +
                "WHEN p.mes =6 THEN CONCAT('JUNIO','-',p.anho) " +
                "WHEN p.mes =7 THEN CONCAT('JULIO','-',p.anho) " +
                "WHEN p.mes =8 THEN CONCAT('AGOSTO','-',p.anho) " +
                "WHEN p.mes =9 THEN CONCAT('SEPTIEMBRE','-',p.anho) " +
                "WHEN p.mes =10 THEN CONCAT('OCTUBRE','-',p.anho) " +
                "WHEN p.mes =11 THEN CONCAT('NOVIEMBRE','-',p.anho) " +
                "WHEN p.mes =12 THEN CONCAT('DICIEMBRE','-',p.anho) " +
                "END as FECHA," +
                "p.total " +
                "from (select month(fecha_pago)as mes,year(fecha_pago)as anho,sum(monto) as total " +
                "from pago where fecha_pago >= date_add(current_date(), interval - 6 month) " +
                "and estado='A' group by month(fecha_pago),year(fecha_pago) order by fecha_pago asc) p";

        return (List<PagoTotalMesesDTO>)jdbcTemplate.query(sql, new RowMapper<PagoTotalMesesDTO>() {
            @Override
            public PagoTotalMesesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                PagoTotalMesesDTO p = new PagoTotalMesesDTO();
                p.setFecha(rs.getString("fecha"));
                p.setTotal(rs.getDouble("total"));
                return p;
            }
        });
    }

}
