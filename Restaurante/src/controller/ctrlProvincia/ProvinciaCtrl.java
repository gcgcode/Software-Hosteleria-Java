package controller.ctrlProvincia;

import java.sql.SQLException;
import java.util.List;
import model.pojo.Mensaje;
import model.pojo.Provincia;
import utils.Pantalla;

public class ProvinciaCtrl {

	public static void list() {

		List<Provincia> lstProvincia = null;
		try {

			lstProvincia = logic.logProvincia.ProvinciaLog.getProvincias();
			Pantalla.mensajeEspaciado(Mensaje.COMANDA_MENU1_TITULO2);
			if (lstProvincia.size() == 0) {
				Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
			} else {
				for (int i = 0; i < lstProvincia.size(); i++) {
					System.out.println((i + 1) + ". " + lstProvincia.get(i).getNombre());
				}
				Pantalla.selectOk();
			}

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			if (errorCode == 2002) {
				Pantalla.error20002();
			} else {
				Pantalla.selectError();
			}
		}

	}

	public static void list(List<Provincia> lstProvincia) {

		Pantalla.mensajeEspaciado(Mensaje.COMANDA_MENU1_TITULO2);
		if (lstProvincia.size() == 0) {
			Pantalla.mensajeError(Mensaje.SELECT_NO_DATA);
		} else {
			for (int i = 0; i < lstProvincia.size(); i++) {
				System.out.println((i + 1) + ". " + lstProvincia.get(i).getNombre());
			}
			Pantalla.selectOk();
		}

	}

}
