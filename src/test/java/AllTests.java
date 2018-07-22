import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.worker.db.DDBBLoginTest;
import com.worker.db.DDBBTest;
import com.worker.models.ManitasTest;
import com.worker.models.UbicacionTest;
import com.worker.persistence.UsuarioEMTest;
import com.worker.persistence.dao.AllDaoTests;
import com.worker.util.HaversineTest;
import com.worker.util.database.DataParserTest;
import com.worker.util.database.DataRetrieverTest;

@RunWith(Suite.class)
@SuiteClasses({
	HaversineTest.class,
	DataParserTest.class,
	DataRetrieverTest.class,
	DDBBLoginTest.class,
	DDBBTest.class,
	ManitasTest.class,
	UbicacionTest.class,
	UbicacionTest.class,
//	MensajeEMTest.class, // REQUIERE: hibernate.cfg.xml de test (hibernate.cfg.xml.test) para el ContextMoking
	UsuarioEMTest.class,
	AllDaoTests.class
})
public class AllTests { }
