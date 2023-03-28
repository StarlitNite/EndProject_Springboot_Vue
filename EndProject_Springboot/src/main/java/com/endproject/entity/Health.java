package com.endproject.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/17 16:17
 */
@Data
@TableName("health_clock")
public class Health {
   private Integer id;
   private String snum;
   private String local;
   private Integer confirmed;
   private Integer asymptomatic;
   private String today_temp;
   private Integer fever_and_cough;
   private String cough;
   private String recent_home;

   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
   private Date create_time;

   private Integer health_status;
}
