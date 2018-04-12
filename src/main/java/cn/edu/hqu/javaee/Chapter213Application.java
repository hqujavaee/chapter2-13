package cn.edu.hqu.javaee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class Chapter213Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DataSourceConfig.class);
		DataSource dataSource=context.getBean(DataSource.class);
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		List<String> result=jdbcTemplate.query("select id,name from Things", new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return "result:id="+rs.getLong("id")+",name="+rs.getString("name");
			}
			
		});
		for(String str:result) {
			System.out.println(str);
		}
		context.close();
	}
}
