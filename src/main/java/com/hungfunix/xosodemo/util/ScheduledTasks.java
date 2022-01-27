//package com.hungfunix.xosodemo.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//@Configuration
//@EnableScheduling
//public class ScheduledTasks {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    /*
//    Moc thoi gian 2000 duoc tinh tu thoi diem bat dau Task
//     */
//    @Scheduled(fixedRate = 2000)
//    public void scheduleTaskWithFixedRate() {
//        System.out.println("Schedule Task with Fixed Rate...");
////        LOGGER.info("Send email to producers to inform quantity sold items");
//    }
//
//    /*
//    Moc thoi gian 2000 duoc tinh tu thoi diem ket thuc Task
//     */
//    @Scheduled(fixedDelay = 2000)
//    public void scheduleTaskWithFixedDelay() {
//        System.out.println("Schedule Task with Fixed Delay...");
////        LOGGER.info("Send email to producers to inform quantity sold items");
//    }
//
//    /*
//    Moc tho gian initialDelay tinh tu khi Boot up
//     */
//    @Scheduled(fixedRate = 2000, initialDelay = 10000)
//    public void scheduleTaskWithInitialDelay() {
//        System.out.println("Schedule Task with initial delay");
//    }
//    /*
//        Log vao giay thu 15 cua moi phut
//        More: https://www.freeformatter.com/cron-expression-generator-quartz.html
//     */
//    @Scheduled(cron = "15 * * * * ?")
//    public void scheduleTaskWithCronExpression() {
//    }
//
//}
