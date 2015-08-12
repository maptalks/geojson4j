# MapTalks-GeoJSON
GeoJSON Serilization and mutual conversion with JSON

GeoJSON基础库, 定义最基本的数据结构和序列化/反序列化方法.

在GeoJSON基础上增加了四种非GeoJSON标准图形, 非标准图形都定义在org.maptalks.gis.core.geojson.ext包中.

* Circle
* Ellipse
* Retangle
* Sector(扇形)

此外GeoJSON还提供了与JSON字符串之间的相互转化方法.

Maptalks-GeoJSON参考了bjornharrtell的开源项目jts2geojson, 在此对bjornharrtell的工作表示感谢:

https://github.com/bjornharrtell/jts2geojson
