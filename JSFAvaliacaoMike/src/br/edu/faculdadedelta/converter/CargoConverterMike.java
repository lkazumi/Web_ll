package br.edu.faculdadedelta.converter;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.faculdadedelta.dao.CargoDAOMike;
import br.edu.faculdadedelta.modelo.CargoMike;


@FacesConverter(value = "cargoConverter")
public class CargoConverterMike implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		
		if (valor != null) {
			CargoDAOMike dao = new CargoDAOMike();
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		
		if (valor != null) {
			return String.valueOf(((CargoMike)valor).getId());
		}
		
		return null;
	}
}
