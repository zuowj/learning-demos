package cn.zuowenjun.cloud;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义token验证过滤器，实现请求验证
 */
@Component
public class AuthFilter extends ZuulFilter {

    private static final Logger logger= LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;//路由执行前
    }

    @Override
    public int filterOrder() {
        return 0;//过滤器优先顺序，数字越小越先执行
    }

    @Override
    public boolean shouldFilter() {
        if(RequestContext.getCurrentContext().getRequest().getRequestURL().toString().contains("/testgit/")){
            return false;
        }
        return true;//是否需要过滤
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Object token = request.getHeader("token");
        //校验token
        Boolean isValid=false;

        if (StringUtils.equals(String.valueOf(token),"zuowenjun.cn.zuul.token.888888")){ //模拟TOKEN验证,验证通过
            isValid=true;
        }

        if (!isValid) {
            logger.error("token验证不通过，禁止访问！");
            ctx.setSendZuulResponse(false);//false表示不发送路由响应给消费端，即不会去路由请求后端服务
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            ctx.setResponseBody("token验证不通过，禁止访问！");
            ctx.setResponseStatusCode(401);
            return null;
        }

        logger.info(String.format("token is %s", token));

        return null;
    }
}
