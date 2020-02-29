webMagic 爬虫框架

四大组件

1.Downloader 下载页面,webMagic默认使用了Httpclient
2.PageProcessor 负责解析页面,抽取有用信息和发现新的url,使用Jsoup作为HTML解析工具，并基于其开发了解析XPath的工具Xsoup。
3.Scheduler 负责一些待抓取的url,以及一些去重的工作.(WebMagic默认提供了JDK的内存队列来管理URL，并用集合来进行去重。也支持使用Redis进行分布式管理)
4.Pipeline负责抽取结果的处理，包括计算、持久化到文件、数据库等。WebMagic默认提供了“输出到控制台”和“保存到文件”两种结果处理方案
