package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * exam
 * @author 
 */
@Data
public class Exam extends ExamKey implements Serializable {
    /**
     * 考试名称
     */
    private String name;

    /**
     * 考试时间
     */
    private String duration;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 状态
     */
    private String state;

    /**
     * 总分
     */
    private Integer totalPoints;

    /**
     * 创建时间
     */
    private Date creationTime;

    /**
     * 考试人数
     */
    private Integer numberOfPeople;

    private static final long serialVersionUID = 1L;
}