package com.ada.quartz.quartz.springboot.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ada.quartz.quartz.springboot.entity.SysJob;
import com.ada.quartz.quartz.springboot.service.ISysJobService;
import com.ada.quartz.quartz.springboot.util.BaseJob;
import com.ada.quartz.quartz.springboot.util.MailUtil;
import com.alibaba.fastjson.JSONObject;

public class TestTask3 implements BaseJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
     * Spring Bean 如何注入到实现了 Job 接口的类中？
     *  如果没有任何配置，注入会报空指针异常
     *
     * 因为定时任务 Job 对象的实例化过程是在 Quartz 中进行的，
     * 而 Service Bean 是由Spring 容器管理的，Quartz 察觉不到 Service Bean 的存在，
     * 所以无法将 Service Bean装配到 Job 对象中
     *
     * 如何解决？
     * 见
     * 将Spring的对象注入到Quartz Job
     * 1.AdaptableJobFactory类
     * 2.MyJobFactory类
     * 3.InitStartSchedule类
     *
     */
    @Autowired
    private ISysJobService sysJobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("开始执行任务3... ...");
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("jobGroup", "mail");
        map.put("jobStatus", "1");
        List<SysJob> jobList= sysJobService.querySysJobList(map);

        if( null == jobList || jobList.size() ==0){
            logger.info("没有状态为可用的发送邮件任务... ...");
        }

        for (SysJob sysJob:jobList) {
            String jobClassName=sysJob.getJobName();
            String jobGroupName=sysJob.getJobGroup();

            if (StringUtils.isNotEmpty(sysJob.getJobDataMap())) {
                JSONObject jd = JSONObject.parseObject(sysJob.getJobDataMap());
                JSONObject data = jd.getJSONObject("data");
                String loginAccount = data.getString("loginAccount");
                String loginAuthCode = data.getString("loginAuthCode");
                String sender = data.getString("sender");
                String recipientsStr = data.getString("recipients");
                String[] recipients = recipientsStr.split(",");
                String emailSubject = data.getString("emailSubject");
                String emailContent = data.getString("emailContent");
                String emailContentType = data.getString("emailContentType");

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                emailSubject = emailSubject + sdf.format(date) ;
                logger.info("开始发送邮件... ...");
                MailUtil.sendEmail(loginAccount,loginAuthCode,sender,recipients,emailSubject,emailContent,emailContentType);
            }else {
                logger.info("JobDataMap为空，没有发送邮件的相关信息... ...");
            }

        }
    }
}
