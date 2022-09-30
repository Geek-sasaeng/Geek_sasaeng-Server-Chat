package shop.geeksasangchat.common.domain;

import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;

@ToString
public class BaseEntity implements Serializable {

    @CreatedDate
    private String createdAt;

    @LastModifiedDate
    private String updatedAt;

    private BaseStatus status;  //알아서 String으로 들어감

    public BaseEntity() {
        this.status = BaseStatus.ACTIVE;
    }

    public void delete(){
        this.status = BaseStatus.INACTIVE;
    }
}
