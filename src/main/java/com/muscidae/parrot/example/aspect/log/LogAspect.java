package com.muscidae.parrot.example.aspect.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@Aspect
@Component
public class LogAspect {


	@Pointcut("execution(public * com.muscidae.parrot.example.controller.*.*.*(..))")
	public void request() { }

	/**
	 * 使用aop的前置通知拦截请求参数信息
	 */
	@Before("request()")
	public void before(JoinPoint joinPoint){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (null == attributes)
			return;
		HttpServletRequest request = attributes.getRequest();
		log.debug("『URL』:{}", request.getRequestURL().toString());
		log.debug("『METHOD』:{}", request.getMethod());
		log.debug("『IP』:{}", request.getRemoteAddr());
		if (logFilter(request, joinPoint))
			return;
		// TODO ELK + Kafka
		if (MediaType.APPLICATION_JSON_VALUE.equals(request.getHeader(HttpHeaders.CONTENT_TYPE))) {
			for (Object arg : joinPoint.getArgs()) {
				if (arg instanceof String)
					log.debug("『ARGS,Str』:{}", arg);
				else
					log.debug("『ARGS』:{}", JSONObject.toJSONString(arg));
			}
		} else {
			Enumeration<String> enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String name = enu.nextElement();
				log.debug("Parameter:『{}』,Values:『{}』", name, request.getParameter(name));
			}
		}
	}

	/**
	 * 使用aop的后置通知处理请求参数信息
	 * @param response
	 */
	@AfterReturning(returning = "response", pointcut = "request()")
	public void afterReturning(Object response){
		// 处理完请求, 返回内容
		log.debug("『RESPONSE』: {}", JSON.toJSONString(response, SerializerFeature.WriteMapNullValue));
	}

	/**
	 * @author muscidae
	 * @date 2019/9/6 16:41
	 * @description 日志过滤
	 * @param request
	 * @param joinPoint
	 * @return boolean
	 */
	private boolean logFilter(HttpServletRequest request, JoinPoint joinPoint){
		//请求参数长度过滤
		if (0 == joinPoint.getArgs().length)
			return true;
		// 请求类型过滤
		if (HttpMethod.GET.toString().equals(request.getMethod())
				|| HttpMethod.POST.toString().equals(request.getMethod())
				|| HttpMethod.PUT.toString().equals(request.getMethod())
				|| HttpMethod.DELETE.toString().equals(request.getMethod()))
			return false;
		return true;
	}



}
