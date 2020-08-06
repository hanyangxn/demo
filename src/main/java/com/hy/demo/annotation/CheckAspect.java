package com.hy.demo.annotation;


import com.hy.demo.util.ParamChekErrorException;
import com.hy.demo.util.ReflectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.function.BiFunction;

/**
 * @author xlqi
 */
@Aspect
@Component
public class CheckAspect {

	private static final String SEPARATOR = ":";
	private final Logger log = LoggerFactory.getLogger(getClass());

	private static Boolean isNotNull(Object value, String operatorNum) {
		Boolean isNotNull = Boolean.TRUE;
		Boolean isStringNull = (value instanceof String) && StringUtils.isEmpty((String) value);
		Boolean isCollectionNull = (value instanceof Collection) && CollectionUtils.isEmpty((Collection) value);
		if (value == null) {
			isNotNull = Boolean.FALSE;
		} else if (isStringNull || isCollectionNull) {
			isNotNull = Boolean.FALSE;
		}
		return isNotNull;
	}

	private static Boolean isGreaterThan(Object value, String operatorNum) {
		Boolean isGreaterThan = Boolean.FALSE;
		if (value == null) {
			return Boolean.FALSE;
		}
		Boolean isStringGreaterThen = (value instanceof String) && ((String) value).length() > Integer.valueOf(operatorNum);
		Boolean isLongGreaterThen = (value instanceof Long) && ((Long) value) > Long.valueOf(operatorNum);
		Boolean isIntegerGreaterThen = (value instanceof Integer) && ((Integer) value) > Integer.valueOf(operatorNum);
		Boolean isShortGreaterThen = (value instanceof Short) && ((Short) value) > Short.valueOf(operatorNum);
		Boolean isFloatGreaterThen = (value instanceof Float) && ((Float) value) > Float.valueOf(operatorNum);
		Boolean isDoubleGreaterThen = (value instanceof Double) && ((Double) value) > Double.valueOf(operatorNum);
		Boolean isCollectionGreaterThen = (value instanceof Collection) && ((Collection) value).size() > Integer.valueOf(operatorNum);
		if (isStringGreaterThen || isLongGreaterThen || isIntegerGreaterThen ||
				isShortGreaterThen || isFloatGreaterThen || isDoubleGreaterThen || isCollectionGreaterThen) {
			isGreaterThan = Boolean.TRUE;
		}
		return isGreaterThan;
	}

	private static Boolean isGreaterThanEqual(Object value, String operatorNum) {
		Boolean isGreaterThanEqual = Boolean.FALSE;
		if (value == null) {
			return Boolean.FALSE;
		}
		Boolean isStringGreaterThenEqual = (value instanceof String) && ((String) value).length() >= Integer.valueOf(operatorNum);
		Boolean isLongGreaterThenEqual = (value instanceof Long) && ((Long) value) >= Long.valueOf(operatorNum);
		Boolean isIntegerGreaterThenEqual = (value instanceof Integer) && ((Integer) value) >= Integer.valueOf(operatorNum);
		Boolean isShortGreaterThenEqual = (value instanceof Short) && ((Short) value) >= Short.valueOf(operatorNum);
		Boolean isFloatGreaterThenEqual = (value instanceof Float) && ((Float) value) >= Float.valueOf(operatorNum);
		Boolean isDoubleGreaterThenEqual = (value instanceof Double) && ((Double) value) >= Double.valueOf(operatorNum);
		Boolean isCollectionGreaterThenEqual = (value instanceof Collection) && ((Collection) value).size() >= Integer.valueOf(operatorNum);
		if (isStringGreaterThenEqual || isLongGreaterThenEqual || isIntegerGreaterThenEqual ||
				isShortGreaterThenEqual || isFloatGreaterThenEqual || isDoubleGreaterThenEqual || isCollectionGreaterThenEqual) {
			isGreaterThanEqual = Boolean.TRUE;
		}
		return isGreaterThanEqual;
	}

	private static Boolean isLessThan(Object value, String operatorNum) {
		Boolean isLessThan = Boolean.FALSE;
		if (value == null) {
			return Boolean.FALSE;
		}
		Boolean isStringLessThen = (value instanceof String) && ((String) value).length() < Integer.valueOf(operatorNum);
		Boolean isLongLessThen = (value instanceof Long) && ((Long) value) < Long.valueOf(operatorNum);
		Boolean isIntegerLessThen = (value instanceof Integer) && ((Integer) value) < Integer.valueOf(operatorNum);
		Boolean isShortLessThen = (value instanceof Short) && ((Short) value) < Short.valueOf(operatorNum);
		Boolean isFloatLessThen = (value instanceof Float) && ((Float) value) < Float.valueOf(operatorNum);
		Boolean isDoubleLessThen = (value instanceof Double) && ((Double) value) < Double.valueOf(operatorNum);
		Boolean isCollectionLessThen = (value instanceof Collection) && ((Collection) value).size() < Integer.valueOf(operatorNum);
		if (isStringLessThen || isLongLessThen || isIntegerLessThen ||
				isShortLessThen || isFloatLessThen || isDoubleLessThen || isCollectionLessThen) {
			isLessThan = Boolean.TRUE;
		}
		return isLessThan;
	}

	private static Boolean isLessThanEqual(Object value, String operatorNum) {
		Boolean isLessThanEqual = Boolean.FALSE;
		if (value == null) {
			return Boolean.FALSE;
		}
		Boolean isStringLessThenEqual = (value instanceof String) && ((String) value).length() <= Integer.valueOf(operatorNum);
		Boolean isLongLessThenEqual = (value instanceof Long) && ((Long) value) <= Long.valueOf(operatorNum);
		Boolean isIntegerLessThenEqual = (value instanceof Integer) && ((Integer) value) <= Integer.valueOf(operatorNum);
		Boolean isShortLessThenEqual = (value instanceof Short) && ((Short) value) <= Short.valueOf(operatorNum);
		Boolean isFloatLessThenEqual = (value instanceof Float) && ((Float) value) <= Float.valueOf(operatorNum);
		Boolean isDoubleLessThenEqual = (value instanceof Double) && ((Double) value) <= Double.valueOf(operatorNum);
		Boolean isCollectionLessThenEqual = (value instanceof Collection) && ((Collection) value).size() <= Integer.valueOf(operatorNum);
		if (isStringLessThenEqual || isLongLessThenEqual || isIntegerLessThenEqual ||
				isShortLessThenEqual || isFloatLessThenEqual || isDoubleLessThenEqual || isCollectionLessThenEqual) {
			isLessThanEqual = Boolean.TRUE;
		}
		return isLessThanEqual;
	}

	private static Boolean isNotEqual(Object value, String operatorNum) {
		Boolean isNotEqual = Boolean.FALSE;
		if (value == null) {
			return Boolean.FALSE;
		}
		Boolean isStringNotEqual = (value instanceof String) && !value.equals(operatorNum);
		Boolean isLongNotEqual = (value instanceof Long) && !value.equals(Long.valueOf(operatorNum));
		Boolean isIntegerNotEqual = (value instanceof Integer) && !value.equals(Integer.valueOf(operatorNum));
		Boolean isShortNotEqual = (value instanceof Short) && !value.equals(Short.valueOf(operatorNum));
		Boolean isFloatNotEqual = (value instanceof Float) && !value.equals(Float.valueOf(operatorNum));
		Boolean isDoubleNotEqual = (value instanceof Double) && !value.equals(Double.valueOf(operatorNum));
		Boolean isCollectionNotEqual = (value instanceof Collection) && ((Collection) value).size() != Integer.valueOf(operatorNum);
		if (isStringNotEqual || isLongNotEqual || isIntegerNotEqual ||
				isShortNotEqual || isFloatNotEqual || isDoubleNotEqual || isCollectionNotEqual) {
			isNotEqual = Boolean.TRUE;
		}
		return isNotEqual;
	}

	@Pointcut("@annotation(com.hy.demo.annotation.ParamCheck)")
	public void paramCheck() {

	}

	@Around("paramCheck()")
	public Object check(ProceedingJoinPoint point) throws Throwable {
		Object obj;
		// 参数校验
		String msg = doCheck(point);
		if (!StringUtils.isEmpty(msg)) {
//			return new BaseResponseDTO(StatusRetCodeEnum.PARAM_ERR, msg);
			throw new ParamChekErrorException(msg);
		}
		obj = point.proceed();
		return obj;
	}

	private String doCheck(ProceedingJoinPoint point) {
		// 获取方法参数值
		Object[] arguments = point.getArgs();
		// 获取方法
		Method method = getMethod(point);
		ParamCheck annotation = method.getAnnotation(ParamCheck.class);
		String[] fields = annotation.value();
		// 默认的错误信息
		String methodInfo = StringUtils.isEmpty(method.getName()) ? "" : " while calling " + method.getName();
		String msg = "";
		if (isBaseType(method.getParameterTypes()[0].getClass())) {
			//基本参数类型-目前只支持一个基本类型参数，因为无法判断参数的名称
			if (null == arguments[0]) {
				String[] arr = fields[0].split(":");
				if (2 == arr.length) {
					msg = arr[1];
				} else {
					msg = arr[0] + " can not be null";
				}
			}
		} else if (isCheck(method, arguments)) {
			// 只支持对第一个参数进行校验
			Object vo = arguments[0];
			if (vo == null) {
				msg = "param can not be null";
			} else {
				for (String field : fields) {
					// 解析字段
					FieldInfo info = resolveField(field, methodInfo);
					// 获取字段的值
					Object value = ReflectionUtil.invokeGetter(vo, info.field);
					// 执行校验规则
					Boolean isValid = info.optEnum.fun.apply(value, info.operatorNum);
					msg = isValid ? msg : info.innerMsg;
				}
			}
		}

		return msg;
	}

	private boolean isBaseType(Class<?> clazz) {
		return clazz.isAssignableFrom(String.class)
				|| clazz.isAssignableFrom(Long.class)
				|| clazz.isAssignableFrom(Integer.class)
				|| clazz.isAssignableFrom(Short.class)
				|| clazz.isAssignableFrom(Double.class)
				|| clazz.isAssignableFrom(Float.class)
				|| clazz.isAssignableFrom(Collection.class);
	}

	private FieldInfo resolveField(String fieldStr, String methodInfo) {
		FieldInfo fieldInfo = new FieldInfo();
		String innerMsg = "";
		// 解析提示信息
		if (fieldStr.contains(SEPARATOR)) {
			innerMsg = fieldStr.split(SEPARATOR)[1];
			fieldStr = fieldStr.split(SEPARATOR)[0];
		}
		// 解析操作符
		if (fieldStr.contains(Operator.GREATER_THAN_EQUAL.value)) {
			fieldInfo.optEnum = Operator.GREATER_THAN_EQUAL;
		} else if (fieldStr.contains(Operator.LESS_THAN_EQUAL.value)) {
			fieldInfo.optEnum = Operator.LESS_THAN_EQUAL;
		} else if (fieldStr.contains(Operator.GREATER_THAN.value)) {
			fieldInfo.optEnum = Operator.GREATER_THAN;
		} else if (fieldStr.contains(Operator.LESS_THAN.value)) {
			fieldInfo.optEnum = Operator.LESS_THAN;
		} else if (fieldStr.contains(Operator.NOT_EQUAL.value)) {
			fieldInfo.optEnum = Operator.NOT_EQUAL;
		} else {
			fieldInfo.optEnum = Operator.NOT_NULL;
		}
		// 不等于空，直接赋值字段
		if (fieldInfo.optEnum == Operator.NOT_NULL) {
			fieldInfo.field = fieldStr;
			fieldInfo.operatorNum = "";
		}
		// 其他操作符，需要分离出字段和操作数
		else {
			fieldInfo.field = fieldStr.split(fieldInfo.optEnum.value)[0];
			fieldInfo.operatorNum = fieldStr.split(fieldInfo.optEnum.value)[1];
		}
		fieldInfo.operator = fieldInfo.optEnum.value;
		// 处理错误信息
		String defaultMsg = fieldInfo.field + " must " + fieldInfo.operator + " " + fieldInfo.operatorNum + methodInfo;
		fieldInfo.innerMsg = StringUtils.isEmpty(innerMsg) ? defaultMsg : innerMsg;
		return fieldInfo;
	}

	private Boolean isCheck(Method method, Object[] arguments) {
		Boolean isCheck = Boolean.TRUE;
		// 只允许有一个参数
		if (!method.isAnnotationPresent(ParamCheck.class)
				|| arguments == null) {
			isCheck = Boolean.FALSE;
		}
		return isCheck;
	}

	private Method getMethod(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		if (method.getDeclaringClass().isInterface()) {
			try {
				method = joinPoint
						.getTarget()
						.getClass()
						.getDeclaredMethod(joinPoint.getSignature().getName(),
								method.getParameterTypes());
			} catch (SecurityException | NoSuchMethodException e) {
				log.error("" + e);
			}
		}
		return method;
	}

	enum Operator {
		/**
		 * 大于
		 */
		GREATER_THAN(">", CheckAspect::isGreaterThan),
		/**
		 * 大于等于
		 */
		GREATER_THAN_EQUAL(">=", CheckAspect::isGreaterThanEqual),
		/**
		 * 小于
		 */
		LESS_THAN("<", CheckAspect::isLessThan),
		/**
		 * 小于等于
		 */
		LESS_THAN_EQUAL("<=", CheckAspect::isLessThanEqual),
		/**
		 * 不等于
		 */
		NOT_EQUAL("!=", CheckAspect::isNotEqual),
		/**
		 * 不为空
		 */
		NOT_NULL("not null", CheckAspect::isNotNull);

		private String value;

		/**
		 * BiFunction：接收字段值(Object)和操作数(String)，返回是否符合规则(Boolean)
		 */
		private BiFunction<Object, String, Boolean> fun;

		Operator(String value, BiFunction<Object, String, Boolean> fun) {
			this.value = value;
			this.fun = fun;
		}
	}

	static class FieldInfo {
		/**
		 * 字段
		 */
		String field;
		/**
		 * 提示信息
		 */
		String innerMsg;
		/**
		 * 操作符
		 */
		String operator;
		/**
		 * 操作数
		 */
		String operatorNum;
		/**
		 * 操作枚举
		 */
		Operator optEnum;
	}


}
