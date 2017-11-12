package by.tiranid.tracker.dao.service;

import by.tiranid.tracker.dao.config.TestDataBaseConfig;
import by.tiranid.tracker.dao.repository.WorkDaysRepository;
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
@DatabaseTearDown(value = WorkDaysServiceTest.TEAR_DOWN)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})

public class WorkDaysServiceTest {

    public static final String TEAR_DOWN = "/dao/data/work_days/dbTearDown.xml";
    public static final String DB_SETUP = "/dao/data/work_days/sampleTestData.xml";
    private static final Long recordSampleId = 1L;
    // see data.work_days.sampleTestData
    private static final String recordSampleWorkDate = "2007-09-02";
    private static final Byte recordSampleIterations = 7;
    private static final String recordSampleWorkTime = "9:13;16:18;";
    @Autowired
    private WorkDaysRepository workDaysRepository;
/*
    private WorkDaysRepositoryCustom workDaysRepositoryCustom;

    @Before
    public void setUp() throws Exception {
        workDaysRepositoryCustom = new WorkDaysRepositoryCustomImpl(workDaysRepository);
    }


    public WorkDaysEntity createTestWorkDaysEntity() throws Exception {
        WorkDaysEntity record = new WorkDaysEntity();

        record.setWorkDate(Date.valueOf(recordSampleWorkDate));
        record.setIterations(recordSampleIterations);
        record.setWorkTime(recordSampleWorkTime);

        return record;
    }


    @Test
    public void testAddRecord() throws Exception {
        WorkDaysEntity record = createTestWorkDaysEntity();
        workDaysRepositoryCustom.addRecord(record);
        WorkDaysEntity record2 = workDaysRepositoryCustom.getFirst();
        Assert.assertEquals(record, record2);
    }


    @Test
    public void testEditRecord() throws Exception {
        WorkDaysEntity record = createTestWorkDaysEntity();
        record.setIterations((byte) 3);
        record = workDaysRepositoryCustom.addRecord(record);
        Byte iterCountTest = 5;
        record.setIterations(iterCountTest);
        // with addRecord the same editing
        workDaysRepositoryCustom.editRecord(record);
        Assert.assertEquals(iterCountTest, workDaysRepositoryCustom.getFirst().getIterations());
    }


    @Test
    @DatabaseSetup(WorkDaysServiceTest.DB_SETUP)
    public void testGetFirstById() throws Exception {
        WorkDaysEntity entity = workDaysRepositoryCustom.getFirstById(recordSampleId);
        Assert.assertEquals(recordSampleId, entity.getId());
    }


    @Test
    @DatabaseSetup(WorkDaysServiceTest.DB_SETUP)
    public void testGetFirstByWorkDate() throws Exception {
        Date dt = Date.valueOf(recordSampleWorkDate);
        WorkDaysEntity entity = workDaysRepositoryCustom.getFirstByWorkDate(dt);
        Assert.assertEquals(dt, entity.getWorkDate());
    }


    @Test
    @DatabaseSetup(WorkDaysServiceTest.DB_SETUP)
    public void testGetFirstByIterations() throws Exception {
        WorkDaysEntity entity = workDaysRepositoryCustom.getFirstByIterations(recordSampleIterations);
        Assert.assertEquals(recordSampleIterations, entity.getIterations());
    }


    @Test
    @DatabaseSetup(WorkDaysServiceTest.DB_SETUP)
    public void testGetFirstByWorkTime() throws Exception {
        WorkDaysEntity entity = workDaysRepositoryCustom.getFirstByWorkTime(recordSampleWorkTime);
        Assert.assertEquals(recordSampleWorkTime, entity.getWorkTime());
    }


    @Test
    @DatabaseSetup(WorkDaysServiceTest.DB_SETUP)
    public void testGetLast() throws Exception {
        WorkDaysEntity record1 = createTestWorkDaysEntity();
        workDaysRepositoryCustom.addRecord(record1);
        WorkDaysEntity record = workDaysRepositoryCustom.getLast();
        Assert.assertEquals(record1, record);
    }

    @Test
    @DatabaseSetup(WorkDaysServiceTest.DB_SETUP)
    public void testGetFirst() throws Exception {
        workDaysRepositoryCustom.addRecord(createTestWorkDaysEntity());
        WorkDaysEntity record = workDaysRepositoryCustom.getFirst();
        Assert.assertEquals(recordSampleId, record.getId());
    }

    @Test
    @DatabaseSetup(WorkDaysServiceTest.DB_SETUP)
    public void testDelete() throws Exception {
        workDaysRepositoryCustom.delete(recordSampleId);
        WorkDaysEntity record2 = workDaysRepositoryCustom.getFirstById(recordSampleId);
        Assert.assertNull(record2);
    }


    @Test
    @DatabaseSetup(WorkDaysServiceTest.DB_SETUP)
    public void testGetByWorkDate() throws Exception {
        workDaysRepositoryCustom.addRecord(createTestWorkDaysEntity());
        List<WorkDaysEntity> list = workDaysRepositoryCustom.getByWorkDate(Date.valueOf(recordSampleWorkDate));
        Assert.assertEquals(2, list.size());
    }

    @Test
    @DatabaseSetup(WorkDaysServiceTest.DB_SETUP)
    public void testGetByIterations() throws Exception {
        workDaysRepositoryCustom.addRecord(createTestWorkDaysEntity());
        List<WorkDaysEntity> list = workDaysRepositoryCustom.getByIterations(recordSampleIterations);
        Assert.assertEquals(2, list.size());
    }*/
}
