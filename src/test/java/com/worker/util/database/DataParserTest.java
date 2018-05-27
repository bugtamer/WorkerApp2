package com.worker.util.database;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.worker.models.Mensaje;
import com.worker.models.Usuario;

public class DataParserTest {

	@Test
	public void test() throws Exception {
		String json = JsonRetriever.getDataFrom(DataSource.usuarios);
		List<Usuario> uList = JsonParser.jsonToUsuarios(json);
		for (Usuario u : uList) {
			System.out.println(u);
		}
		fail("DataParserTest Not yet implemented");
	}

//	@Test
	public void test3() throws Exception {
		String json = JsonRetriever.getDataFrom(DataSource.chatMessages);
		List<Mensaje> mList = JsonParser.jsonToMensajes(json);
		for (Mensaje m : mList) {
			System.out.println(m);
		}
		fail("DataParserTest Not yet implemented");
	}

}
