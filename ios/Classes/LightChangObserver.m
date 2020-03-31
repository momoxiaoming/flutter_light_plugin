//
//  LightChangObserver.m
//  flutter_light_plugin
//
//  Created by 张小明 on 2020/3/28.
//

#import "LightChangObserver.h"

@implementation LightChangObserver




//设置亮度
+(void)setLight:(double)light{
    if(light>1.0){
        light=1.0;
    }
    if(light<0){
        light=0.0;
    }
    [[UIScreen mainScreen] setBrightness:light];
}

/// 获取最大亮度
+(double)getMaxLight{
    
    
    return 1.0;
}

///  获取当前亮度
+(double)getCurrentLight{
    
    return [UIScreen mainScreen].brightness;
}
@end
