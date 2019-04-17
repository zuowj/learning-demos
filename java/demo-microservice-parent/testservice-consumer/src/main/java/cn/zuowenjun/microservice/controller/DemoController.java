package cn.zuowenjun.microservice.controller;

import cn.zuowenjun.microservice.Remote.DemoRemoteService;
import cn.zuowenjun.microservice.model.ApiResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
public class DemoController {

    private DemoRemoteService demoRemoteService;

    @Autowired
    public DemoController(DemoRemoteService demoRemoteService) {
        this.demoRemoteService = demoRemoteService;
    }

    @RequestMapping(value = "/consumer/demo/showmsg/{name}",method = {RequestMethod.GET,RequestMethod.POST})
    public ApiResult<String> showMessage(@PathVariable String name) {
        String msg = demoRemoteService.getMessage(name) + ",request Time:" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        return new ApiResult<String>() {//{setMethod}默认初始化器
            {
                setCode(0);
                setSuccess(true);
                setData(msg);
                setMsg("demoRemoteService.getMessage调用成功！");
            }
        };

    }

    @RequestMapping(value = "/consumer/demo/shownums/{range}",method = {RequestMethod.GET,RequestMethod.POST})
    public ApiResult<Map<String, Object>> showNumbers(@PathVariable String range){
        ApiResult<Map<String, Object>> apiResult=new ApiResult<>();
        if(StringUtils.isBlank(range) || !range.contains("-")){
            apiResult.setCode(1);
            apiResult.setSuccess(false);
            apiResult.setMsg("数字区间输入不正确，必需是min-max,如：1-10");
            return apiResult;
        }

        int[] rangeNums= Arrays.stream(range.split("-")).mapToInt(value -> Integer.parseInt(value)).toArray();
        apiResult.setData(demoRemoteService.getNumbers(rangeNums[0],rangeNums[1]));
        apiResult.setCode(0);
        apiResult.setMsg("demoRemoteService.getNumbers调用成功！");

        return apiResult;
    }




}
