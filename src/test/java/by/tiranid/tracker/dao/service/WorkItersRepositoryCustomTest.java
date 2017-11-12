package by.tiranid.tracker.dao.service;

import by.tiranid.tracker.dao.config.TestDataBaseConfig;
import by.tiranid.tracker.dao.repository.WorkItersRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class, loader = AnnotationConfigContextLoader.class)
@DatabaseTearDown(value = WorkItersRepositoryCustomTest.TEAR_DOWN)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})

public class WorkItersRepositoryCustomTest {

    public static final String TEAR_DOWN = "/dao/data/work_iters/dbTearDown.xml";
    public static final String DB_SETUP = "/dao/data/work_iters/sampleTestData.xml";
    // see data.record.sampleTestData
    private static final Long recordSampleId = 1L;
    private static final String recordSampleDdate = "2007-09-02";
    private static final String recordSampleTtime = "04:20:00";
    private static final String recordSampleDuration = "00:25:00";
    @Autowired
    private WorkItersRepository workItersRepository;
/*
    private WorkItersRepositoryCustom workItersRepositoryCustom;

    @Before
    public void setUp() throws Exception {
        workItersRepositoryCustom = new WorkItersRepositoryCustomImpl(workItersRepository);
    }


    public WorkItersEntity createTestWorkItersEntity() throws Exception {

        WorkItersEntity record = new WorkItersEntity();
        record.setDdate(Date.valueOf(recordSampleDdate));
        record.setTtime(Time.valueOf(recordSampleTtime));
        record.setDuration(Time.valueOf(recordSampleDuration));
        return record;
    }


    @Test
    public void testAddRecord() throws Exception {
        WorkItersEntity record = createTestWorkItersEntity();
        workItersRepositoryCustom.addRecord(record);
        WorkItersEntity record2 = workItersRepositoryCustom.getFirst();
        Assert.assertEquals(record, record2);
    }


    @Test
    public void testEditRecord() throws Exception {
        WorkItersEntity record = createTestWorkItersEntity();
        record.setDdate(Date.valueOf("1488-08-14"));
        record = workItersRepositoryCustom.addRecord(record);
        Date dt = Date.valueOf("1488-08-01");
        record.setDdate(dt);
        // with addRecord the same editing
        workItersRepositoryCustom.editRecord(record);
        Assert.assertEquals(dt, workItersRepositoryCustom.getFirst().getDdate());
    }


    @Test
    @DatabaseSetup(WorkItersRepositoryCustomTest.DB_SETUP)
    public void testGetFirstById() throws Exception {
        WorkItersEntity entity = workItersRepositoryCustom.getFirstById(recordSampleId);
        Assert.assertEquals(recordSampleId, entity.getId());
    }


    @Test
    @DatabaseSetup(WorkItersRepositoryCustomTest.DB_SETUP)
    public void testGetFirstByDdate() throws Exception {
        Date dt = Date.valueOf(recordSampleDdate);
        WorkItersEntity entity = workItersRepositoryCustom.getFirstByDdate(dt);
        Assert.assertEquals(dt, entity.getDdate());
    }


    @Test
    @DatabaseSetup(WorkItersRepositoryCustomTest.DB_SETUP)
    public void testGetFirstByTtime() throws Exception {
        Time t = Time.valueOf(recordSampleTtime);
        WorkItersEntity entity = workItersRepositoryCustom.getFirstByTtime(t);
        Assert.assertEquals(t, entity.getTtime());
    }

    @Test
    @DatabaseSetup(WorkItersRepositoryCustomTest.DB_SETUP)
    public void testGetFirstByDuration() throws Exception {
        Time t = Time.valueOf(recordSampleDuration);
        WorkItersEntity entity = workItersRepositoryCustom.getFirstByDuration(t);
        Assert.assertEquals(t, entity.getDuration());
    }


    @Test
    @DatabaseSetup(WorkItersRepositoryCustomTest.DB_SETUP)
    public void testGetLast() throws Exception {
        WorkItersEntity record1 = createTestWorkItersEntity();
        workItersRepositoryCustom.addRecord(record1);
        WorkItersEntity record = workItersRepositoryCustom.getLast();
        Assert.assertEquals(record1, record);
    }

    @Test
    @DatabaseSetup(WorkItersRepositoryCustomTest.DB_SETUP)
    public void testGetFirst() throws Exception {
        workItersRepositoryCustom.addRecord(createTestWorkItersEntity());
        WorkItersEntity record = workItersRepositoryCustom.getFirst();
        Assert.assertEquals(recordSampleId, record.getId());
    }

    @Test
    @DatabaseSetup(WorkItersRepositoryCustomTest.DB_SETUP)
    public void testDelete() throws Exception {
        workItersRepositoryCustom.delete(recordSampleId);
        WorkItersEntity record2 = workItersRepositoryCustom.getFirstById(recordSampleId);
        Assert.assertNull(record2);
    }

    @Test
    @DatabaseSetup(WorkItersRepositoryCustomTest.DB_SETUP)
    public void testGetByDdate() throws Exception {
        workItersRepositoryCustom.addRecord(createTestWorkItersEntity());
        List<WorkItersEntity> list = workItersRepositoryCustom.getByDdate(Date.valueOf(recordSampleDdate));
        Assert.assertEquals(2, list.size());
    }

    @Test
    @DatabaseSetup(WorkItersRepositoryCustomTest.DB_SETUP)
    public void testGetByDuration() throws Exception {
        workItersRepositoryCustom.addRecord(createTestWorkItersEntity());
        List<WorkItersEntity> list = workItersRepositoryCustom.getByDuration(Time.valueOf(recordSampleDuration));
        Assert.assertEquals(2, list.size());
    }*/
}
