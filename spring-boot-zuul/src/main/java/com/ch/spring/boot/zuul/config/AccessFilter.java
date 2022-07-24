package com.ch.spring.boot.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>accessFilter 过滤器</p>
 *
 * @ClassName AccessFilter
 * @Description accessFilter 过滤器
 * @Author zhaohongliang
 * @Date 2022-07-24 21:31
 * @Version 1.0
 */
@Component
public class AccessFilter extends ZuulFilter {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器的类型
     * pre
     * routing
     * post
     * error
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序
     * 数值越小，优先级越高
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否要被执行
     * true  开启
     * false 关闭
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        //  请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        String acccessToken = request.getParameter("accessToken");
        logger.info ("accessToken: {}", acccessToken);
        if (null == acccessToken) {
            logger.warn("accessToke is empty!");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            try {
                writer = ctx.getResponse().getWriter();
                // 响应内容
                writer.print("{\"message\":" + 401 + "}");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != writer) {
                    writer.close();
                }
            }

        } else {
            logger.info("accessToken is ok!");
        }

        return null;

    }
}
