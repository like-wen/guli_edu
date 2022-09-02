import com.lkw.eduservice.entity.EduTeacher;
import com.lkw.eduservice.service.EduTeacherService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class teacherTest {
    @Autowired
    EduTeacherService eduTeacherService;
    @Test
    public void test1(){
        EduTeacher eduTeacher = new EduTeacher();
        eduTeacher.setName("lkw");


        boolean save = eduTeacherService.save(eduTeacher);

    }
}
