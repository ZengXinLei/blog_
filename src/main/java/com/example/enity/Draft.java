package com.example.enity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Draft {

  private String did;
@ApiModelProperty("�ݸ�id")
  private String dtitle;
@ApiModelProperty("�ݸ�ı���")
  private String dtext;
@ApiModelProperty("�ݸ��text����")
  private String dhtml;
@ApiModelProperty("�ݸ�HTML����")
  private long dtime;
@ApiModelProperty("�ݸ��ʱ��")
  private String dstate;
@ApiModelProperty("�ݸ��״̬")
  private long duserId;
@ApiModelProperty("�ݸ�Ĺ���Աid")
  private String dtype;
@ApiModelProperty("�ݸ������")

  private User user;


}
