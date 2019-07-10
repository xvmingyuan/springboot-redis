# 1.自定义一个注解

        创建一个 @interface的文件,则表示这是一个注解类
        /*
         * 自定义注解
         */
        @Target({ElementType.METHOD})
        @Retention(RetentionPolicy.RUNTIME)
        @Documented
        @Inherited
        public @interface SecureValid {
            String desc() default "身份和安全验证开始...";
        }
# 2.注解的属性描述

        2.1 @Target 
        用于描述注解的使用范围（即：被描述的注解可以用在什么地方），其取值有：
        
        ElementType.CONSTRUCTOR                用于描述构造器。
        ElementType.FIELD                                用于描述域。
        ElementType.LOCAL_VARIABLE             用于描述局部变量
        ElementType.METHOD                          用于描述方法
        ElementType.PACKAGE                         用于描述包
        ElementType.PARAMETER                     用于描述参数
        ElementType.TYPE                                 用于描述类或接口
        
        2.2 @Retention
        用于描述注解的生命周期（即：被描述的注解在什么范围内有效），其取值有：
        RetentionPolicy.SOURCE                        在源文件中有效（即源文件保留）。
        RetentionPolicy.CLASS                           在 class 文件中有效（即 class 保留）
        RetentionPolicy.RUNTIME                      在运行时有效（即运行时保留）
        
        2.3 @Documented
        在默认的情况下javadoc命令不会将我们的annotation生成再doc中去的，所以使用该标记就是告诉jdk让它也将annotation生成到doc中去
        
        2.4 @Inherited
         比如有一个类A，在他上面有一个标记annotation，那么A的子类B是否不用再次标记annotation就可以继承得到
# 3.Annotation属性值

        有以下三种： 基本类型、数组类型、枚举类型
        
        3.1 基本串类型 
        public @interface UserdefinedAnnotation {  
            intvalue();  
            String name() default "zhangsan";  
            String address();  
        }
        使用：
        @UserdefinedAnnotation(value=123,name="zhangsan",address="火星")  
            public static void main(String[] args) {  
                System.out.println("hello");  
            }  
        }
        3.2 数组
        public @interface UserdefinedAnnotation {  
            int[] value();  
        }  
        使用：  
        public class UseAnnotation {  
              
            @UserdefinedAnnotation({123})  
            public static void main(String[] args) {  
                System.out.println("hello");  
            }  
        } 
        3.3 枚举类型
        public enum DateEnum {  
            Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday  
        }  
        然后在定义一个annotation  
        package com.wangwenjun.annatation.userdefined;  
          
        public @interface UserdefinedAnnotation {  
            DateEnum week();  
        }  
        使用： 
        public class UseAnnotation {  
            @UserdefinedAnnotation(week=DateEnum.Sunday)  
            public static void main(String[] args) {  
                System.out.println("hello");  
            }  
        }
        
# 4.使用SpringAOP来增强

        @Aspect
        @Component
        public class Aspect {
        
            private static final Logger logger = LoggerFactory.getLogger(Aspect.class);
        
            //切入点
            @Pointcut("@annotation(注解的包路径)")
            public void pointcut() {
            }
        
            @Around("pointcut()")
            public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
                    //获取request
                    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
                    //拦截的实体类
                    Object target = joinPoint.getTarget();
                    //拦截的方法名称
                    String methodName = joinPoint.getSignature().getName();
                    //拦截的方法参数
                    Object[] args = joinPoint.getArgs();
                    //拦截的放参数类型
                    Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        
                    
                     //TODO 处理业务代码
        
                     
        
                    //处理完之后放行
                    Object[] args = joinPoint.getArgs();
                    return joinPoint.proceed(args);
            }
              
        }