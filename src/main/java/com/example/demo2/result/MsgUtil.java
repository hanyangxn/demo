package com.example.demo2.result;


import com.example.demo2.exception.ManagementCockpitException;
import com.example.demo2.util.CONST;
import com.example.demo2.util.ResultDto;
import com.example.demo2.util.ResultUtil;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class MsgUtil {

	public static <T> ResultDto<T> success(String msgCd, MessageSource msgsrc, String... langAndRegion) {
		return success(null, msgCd, msgsrc);
	}

	public static <T> ResultDto<T> success(T obj, String msgCd, MessageSource msgsrc, String... langAndRegion) {
		String msg = getMessage(msgCd, msgsrc, langAndRegion);
		return ResultUtil.success(obj, msgCd, msg);
	}

	public static ManagementCockpitException warning(String msgCd, MessageSource msgsrc, String... langAndRegion) {
		String msg = getMessage(msgCd, msgsrc, langAndRegion);
		return new ManagementCockpitException(msgCd, msg);
	}

	public static ManagementCockpitException warning(String[] args, String msgCd, MessageSource msgsrc, String... langAndRegion) {
		String msg = getMessage(msgCd, msgsrc, langAndRegion);
		for(int i = 0; i < args.length; i++) {
			msg = msg.replaceAll("\\{arg" + (i + 1) + "\\}", args[i]);
		}
		return new ManagementCockpitException(msgCd, msg);
	}

	private static String getMessage(String msgCd, MessageSource msgsrc, String... langAndRegion) {
		String msg;
		if(langAndRegion.length == 2) {
			msg = msgsrc.getMessage(msgCd, null, new Locale(langAndRegion[0], langAndRegion[1]));
		} else {
			msg = msgsrc.getMessage(msgCd, null, new Locale(CONST.LANG_ZH, CONST.RGN_CN));
		}
		return msg;
	}

}
