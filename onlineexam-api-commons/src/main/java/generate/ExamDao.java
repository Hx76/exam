package generate;

import generate.Exam;
import generate.ExamKey;

public interface ExamDao {
    int deleteByPrimaryKey(ExamKey key);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(ExamKey key);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);
}