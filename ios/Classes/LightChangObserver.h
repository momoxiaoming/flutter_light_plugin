//
//  LightChangObserver.h
//  flutter_light_plugin
//
//  Created by 张小明 on 2020/3/28.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface LightChangObserver : NSObject


//设置亮度
+(void)setLight:(double)light;

/// 获取最大亮度
+(double)getMaxLight;

///  获取当前亮度
+(double)getCurrentLight;

@end

NS_ASSUME_NONNULL_END
