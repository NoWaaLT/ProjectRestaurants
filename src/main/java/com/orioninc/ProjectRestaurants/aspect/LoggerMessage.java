//package com.orioninc.ProjectRestaurants.aspect;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
//import lombok.*;
//
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//public class LoggerMessage {
//
//    private String className;
//    private String methodName;
//    private String methodArgs;
//    private Long elapsedTimeInMillis;
//    private Long elapsedTimeInMicros;
//    private Throwable throwable;
//    private Object result;
//
//    @SneakyThrows
//    @Override
//    public String toString() {
//
////        ObjectMapper objectMapper = new ObjectMapper();     // TODO make this class do the job
////        objectMapper.registerModule(new Jdk8Module());
//
//        return "LoggerMessage {" +
//                "className='" + className + '\'' +
//                ", methodName='" + methodName + '\'' +
//                ", methodArgs='" + methodArgs + '\'' +
//                ", elapsedTimeInMillis=" + elapsedTimeInMillis +
//                ", elapsedTimeInMicros=" + elapsedTimeInMicros +
//                ", throwable=" + throwable +
//                ", result=" + objectMapper.writeValueAsString(result) +
//                '}';
//    }
//}
