package shop.geeksasangchat.common.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@ToString
@Getter
public class BaseEntity implements Serializable {

    @CreatedDate
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt; // 등록일

    @LastModifiedDate
    private Timestamp updatedAt;

    private BaseStatus status;  //알아서 String으로 들어감

    public BaseEntity() {
        this.status = BaseStatus.ACTIVE;
    }

    public void delete(){
        this.status = BaseStatus.INACTIVE;
    }
}
