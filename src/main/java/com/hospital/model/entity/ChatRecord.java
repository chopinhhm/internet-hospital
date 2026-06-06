package com.hospital.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("chat_record")
public class ChatRecord {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String orderId;
    private String senderId;
    private String senderRole;
    private String type;
    private String content;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
