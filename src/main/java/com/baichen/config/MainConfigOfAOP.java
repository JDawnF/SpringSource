package com.baichen.config;

import com.baichen.aop.LogAspects;
import com.baichen.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Program: MainConfigOfAOP
 * @Author: baichen
 * @Description: AOP配置类
 * AOP：【动态代理】指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式；
 * 1、导入aop模块依赖； Spring AOP：(spring-aspects)
 * 2、定义一个业务逻辑类（MathCalculator）:在业务逻辑运行的时候将日志进行打印（方法之前、方法运行结束、方法出现异常，xxx）
 * 3、定义一个日志切面类（LogAspects）:切面类里面的方法需要动态感知MathCalculator类运行到哪里然后执行；
 * 		通知方法：
 * 			前置通知(@Before)：logStart：在目标方法(div)运行之前运行
 * 			后置通知(@After)：logEnd：在目标方法(div)运行结束之后运行（无论方法正常结束还是异常结束）
 * 			返回通知(@AfterReturning)：logReturn：在目标方法(div)正常返回之后运行
 * 			异常通知(@AfterThrowing)：logException：在目标方法(div)出现异常以后运行
 * 			环绕通知(@Around)：动态代理，手动推进目标方法运行（joinPoint.proceed()）
 * 4、给切面类的目标方法标注何时何地运行（通知注解，即要拦截通知的方法）；
 * 5、将切面类和业务逻辑类（目标方法所在类）都加入到容器中，可以通过在配置类中加入@Bean注解，然后创建这两个类;
 * 6、必须告诉Spring哪个类是切面类(给切面类上加一个注解：@Aspect)
 * [7]、在配置类中加 @EnableAspectJAutoProxy 【开启基于注解的aop模式】，即启用AspectJ自动代理
 * 		在Spring中很多的 @EnableXXX,表示开启某种功能
 * 三步：
 * 	1）、将业务逻辑组件和切面类都加入到容器中,告诉Spring哪个是切面类（通过@Aspect实现）
 * 	2）、在切面类上的每一个通知方法上标注通知的相关注解，告诉Spring何时何地运行（切入点表达式，execution表达式）
 *  3）、开启基于注解的aop模式，@EnableAspectJAutoProxy，如这里是在配置类中标注
 *
 * AOP原理：【看给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么？】
 * 		@EnableAspectJAutoProxy: 主要是引入AspectJAutoProxyRegistrar这个类，注册自定义组件
 * 1、@EnableAspectJAutoProxy：
 * 		@Import(AspectJAutoProxyRegistrar.class)： 给容器中导入AspectJAutoProxyRegistrar类
 * 		利用AspectJAutoProxyRegistrar的registerBeanDefinitions方法，注册自定义组件，可以给容器中注册bean,
 * 		registerBeanDefinitions方法中调用了AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry); 会返回一个BeanDefinition
 * 	    registerAspectJAnnotationAutoProxyCreatorIfNecessary方法最终调用的是registerOrEscalateApcAsRequired方法，会注册自定义bean并返回BeanDefinition
 * 		最终(AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME)internalAutoProxyCreator就是AnnotationAwareAspectJAutoProxyCreator
 * 		给容器中注册一个AnnotationAwareAspectJAutoProxyCreator，自动代理创建器
 * 2、 AnnotationAwareAspectJAutoProxyCreator：
 * AnnotationAwareAspectJAutoProxyCreator 父类->AspectJAwareAdvisorAutoProxyCreator 父类
 * ->AbstractAdvisorAutoProxyCreator 父类->AbstractAutoProxyCreator
 * ->implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware 关注后置处理器（在bean初始化完成前后做事情）、自动装配BeanFactory
 * (可以在以下方法打断点)
 * AbstractAutoProxyCreator.setBeanFactory(BeanFactory beanFactory)   赋值当前对象的beanFactory给beanFactory参数
 * AbstractAutoProxyCreator.有后置处理器的逻辑,即postProcessXxx
 *
 * AbstractAdvisorAutoProxyCreator.setBeanFactory(),在这个方法中调用了initBeanFactory(),初始化bean工厂
 * AnnotationAwareAspectJAutoProxyCreator.initBeanFactory(),在父类中会调用setBeanFactory
 * 流程：
 * 		1）、在测试类IOCTest_AOP中，先通过AnnotationConfigApplicationContext传入配置类(MainConfigOfAOP.class)，创建ioc容器
 * 		2）、注册配置类，调用refresh（）刷新容器,有点类似初始化容器；
 * 		3）、在refresh方法中，有个registerBeanPostProcessors(beanFactory):可以注册拦截bean的创建的bean的后置处理器；
 * 			1）、通过 beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);
 * 先获取ioc容器中已经定义了的，需要创建对象的所有BeanPostProcessor(容器有一些默认的后置处理器还有在配置类中通过@EnableAspectJAutoProxy引入的AspectJAutoProxyRegistrar.java)
 * 			2）、给容器中加别的BeanPostProcessor
 * 			3）、优先注册实现了PriorityOrdered接口(顺序)的BeanPostProcessor；
 * 			4）、再给容器中注册实现了Ordered接口的BeanPostProcessor；(PriorityOrdered继承了Ordered接口)
 * 			5）、注册没实现优先级接口的BeanPostProcessor；
 * 			6）、注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中；
 * 				创建internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator类型】
 * 			后面可以拦截这些创建过程，做相应的处理
 * 				1）、创建Bean的实例
 * 				2）、populateBean；给bean的各种属性赋值
 * 				3）、initializeBean：初始化bean(后置处理器其实就是在初始化bean的前后进行工作)
 * 					1）、invokeAwareMethods()：先判断bean对象是否为Aware接口，如果是则调用相关Aware接口的方法，即处理Aware接口的方法回调
 * 					2）、applyBeanPostProcessorsBeforeInitialization()：应用后置处理器的postProcessBeforeInitialization（）,拿到所有的后置处理器，调用其postProcessBeforeInitialization（）
 * 					3）、invokeInitMethods()：执行自定义的初始化方法
 * 					4）、applyBeanPostProcessorsAfterInitialization()；执行后置处理器的postProcessAfterInitialization（），与上面的 3 类似
 * 				4）、BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功 --》aspectJAdvisorsBuilder
 * 			7）、把BeanPostProcessor注册到BeanFactory中：beanFactory.addBeanPostProcessor(postProcessor);
 *============以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程===================
 * 			AnnotationAwareAspectJAutoProxyCreator => InstantiationAwareBeanPostProcessor
 *
 * 		4）、finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作,即创建剩下的单实例bean
 * 			1）、遍历获取容器中所有的Bean(beanDefinitionName)，依次调用getBean(beanName)创建对象;
 * 				流程(第一次创建bean)：getBean->doGetBean()->getSingleton()->(创建bean实例，先看缓存中是否存在当前bean实例)
 * 			2）、创建bean
 * 				【AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截，因为后置处理器InstantiationAwareBeanPostProcessor会调用postProcessBeforeInstantiation()】
 * 				1）、(单例获取到的变量名是sharedInstance)先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用，否则再创建；只要创建好的Bean都会被缓存起来
 * 				2）、调用createBean() 创建bean ，步骤：
 * 					AnnotationAwareAspectJAutoProxyCreator 会在任何bean创建之前先尝试返回bean的实例，两个后置处理器的不同：
 * 					【BeanPostProcessor  是在Bean对象创建完成，初始化前后调用的，在任何bean创建之前都会调用postProcessBeforeInstantiation】
 * 					【InstantiationAwareBeanPostProcessor是在创建Bean实例之前，先尝试用后置处理器返回对象的】
 * 					1）、resolveBeforeInstantiation(beanName, mbdToUse);  解析BeforeInstantiation
 * 						希望后置处理器在此能返回一个代理对象，如果能返回代理对象就使用，如果不能就继续创建bean
 * 						1）、后置处理器先尝试返回对象：
 * 							bean = applyBeanPostProcessorsBeforeInstantiation（）：
 * 							拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor，就执行postProcessBeforeInstantiation
 * 							if (bean != null) {
                                bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
                            }
 * 					2）、doCreateBean(beanName, mbdToUse, args);  真正的去创建一个bean实例，和上面流程中的3.6一样；
 * AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】	的作用：
 * 1）、在每一个bean创建之前，调用postProcessBeforeInstantiation()；
 * 		主要关注：逻辑类MathCalculator和切面类LogAspect的创建
 * 		1）、判断当前bean是否在advisedBeans中（advisedBeans保存了所有需要增强的bean，增强的bean需要通过切面来实现相应的操作）
 * 		2）、判断当前bean是否是基础类型,即判断是否实现了Advice、Pointcut、Advisor、AopInfrastructureBean等接口，或者是否是切面（@Aspect）
 * 		3）、是否需要跳过
 * 		    1）、获取候选的增强器（即切面里面的通知方法）【将通知方法封装为：List<Advisor> candidateAdvisors】
 * 每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor类型；判断每一个增强器是否是 AspectJPointcutAdvisor 类型的,如果是则返回true
 * 			2）、这里不是AspectJPointcutAdvisor类型的，所以永远返回false
 * 2）、创建逻辑类MathCalculator和切面类LogAspect 对象
 *  postProcessAfterInitialization； return wrapIfNecessary(bean, beanName, cacheKey);//包装如果需要的情况下
 * 		1）、获取当前bean的所有增强器（通知方法） 即 Object[]  specificInterceptors
 * 			1、找到候选的所有的增强器（找哪些通知方法是需要切入当前bean方法的）
 * 			2、获取到能在bean使用的增强器
 * 			3、给增强器排序
 * 		2）、保存当前bean在advisedBeans中；
 * 		3）、如果当前bean需要增强，创建当前bean的代理对象；
 * 			1）、获取所有增强器（通知方法）
 * 			2）、保存到proxyFactory
 * 			3）、通过proxyFactory的getProxy()创建代理对象：Spring自动决定，有以下两种方式
 * 				JdkDynamicAopProxy(config); 实现了接口的，通过jdk动态代理；
 * 				ObjenesisCglibAopProxy(config); 未实现接口 cglib的动态代理；
 * 		4）、给容器中返回当前组件使用cglib增强了的代理对象；
 * 		5）、以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程；
 * 	3）、目标方法(测试类中的mathCalculator.div(1, 1))的执行:
 * 		容器中保存了组件的代理对象（此时是cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象，xxx）；
 * 		1）、CglibAopProxy.intercept();   拦截目标方法的执行
 * 		2）、根据ProxyFactory对象获取将要执行的目标方法拦截器链:
 * 			List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 * 			1）、List<Object> interceptorList保存了所有拦截器，默认初始长度是：5,一个是默认的ExposeInvocationInterceptor 和 4个增强器；
 * 		        getInterceptorsAndDynamicInterceptionAdvice是DefaultAdvisorChainFactory类中的方法
 * 			2）、遍历所有的增强器，将其转为Interceptor：registry.getInterceptors(advisor);
 * 			3）、将增强器转为List<MethodInterceptor>：
 * 		    	如果是MethodInterceptor，直接加入到集合中
 * 				如果不是，使用AdvisorAdapter将增强器转为MethodInterceptor,转换完成返回MethodInterceptor数组；
 * 		3）、如果没有拦截器链，直接执行目标方法:拦截器链（即每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 * 		4）、如果有拦截器链，把需要执行的目标对象，目标方法，拦截器链等信息传入,然后创建一个 CglibMethodInvocation 对象，并调用 Object retVal =  mi.proceed();
 * 		5）、拦截器链的触发过程:
 * 			1)、如果没有拦截器执行执行目标方法，或者拦截器的索引和拦截器数组长度 -1 大小一样（即执行到了最后一个拦截器）执行目标方法；
 * 			2)、链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行；
 * 				拦截器链的机制，保证通知方法与目标方法的执行顺序；
 * 	总结：
 * 		1）、@EnableAspectJAutoProxy 开启AOP功能
 * 		2）、@EnableAspectJAutoProxy 会给容器中注册一个组件 AnnotationAwareAspectJAutoProxyCreator
 * 		3）、AnnotationAwareAspectJAutoProxyCreator是一个后置处理器；
 * 		4）、容器的创建流程(后置处理器的创建和构造过程)：
 * 			1）、registerBeanPostProcessors（）注册后置处理器,创建AnnotationAwareAspectJAutoProxyCreator对象
 * 			2）、finishBeanFactoryInitialization（）初始化剩下的单实例bean
 * 				1）、创建业务逻辑组件和切面组件
 * 				2）、AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 * 				3）、组件创建完之后，判断组件是否需要增强
 * 					是的话：切面的通知方法，包装成增强器（Advisor）,给业务逻辑组件创建一个代理对象（默认是cglib，如果是接口则采用jdk动态代理）；
 * 		5）、执行目标方法：
 * 			1）、代理对象执行目标方法
 * 			2）、CglibAopProxy.intercept()；
 * 				1）、得到目标方法的拦截器链（即将增强器包装成拦截器MethodInterceptor）
 * 				2）、利用拦截器的链式机制，依次进入每一个拦截器进行执行；
 * 				3）、效果：
 * 					正常执行：前置通知-》目标方法-》后置通知-》返回通知
 * 					出现异常：前置通知-》目标方法-》后置通知-》异常通知
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {
    //业务逻辑类加入容器中
    @Bean
    public MathCalculator calculator(){
        return new MathCalculator();
    }

    //切面类加入到容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
