package com.endproject.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/17 16:17
 */
@Data
public class HealthClock {
   private Integer id;
   private String snum;
   private String local;
   private Integer confirmed;
   private Integer asymptomatic;
   private String today_temp;
   private Integer fever_and_cough;
   private String cough;
   private String recent_home;
   private Date create_time;
}
