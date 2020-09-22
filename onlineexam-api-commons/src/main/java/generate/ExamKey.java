package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * exam
 * @author 
 */
@Data
public class ExamKey implements Serializable {
    private Integer id;

    /**
     * 考试编号
     */
    private Integer examSerialNumber;

    private static final long serialVersionUID = 1L;
}