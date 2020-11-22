### MineralSpawner *-* 矿物产生者
![](https://img.shields.io/github/license/Score2/MineralSpawner?color=blue&style=for-the-badge)
![](https://img.shields.io/github/downloads/Score2/MineralSpawner/total?color=green&style=for-the-badge)
![](https://img.shields.io/github/v/release/Score2/MineralSpawner?color=purple&style=for-the-badge)
![](https://img.shields.io/github/issues/Score2/MineralSpawner?style=for-the-badge)
![](https://img.shields.io/github/issues-pr/Score2/MineralSpawner?style=for-the-badge)

***
> Starting from v0.12, there is no need to install [KotlinAPI](https://www.mcbbs.net/thread-1080136-1-1.html).
>
> 自 v0.12 版本开始, 无需再安装 [KotlinAPI](https://www.mcbbs.net/thread-1080136-1-1.html).
##### Features | 特性:
* Support 1.16 nether ores spawner (blue ice above any level junction block of magma, soul soil below).
* Play sounds when ores are spawned (the sound can be randomized through the PlaceholderAPI grammar).
* Support different stone scrubbers in different worlds.
* The mineral probability can be any value without considering its percentage.
* Configuration file nodes can ignore case.
* The plugin is open source on GitHub under the GPLv3.0 agreement, please make sure to use the plugin in compliance with the agreement.
* 支持 1.16 地狱刷石机(在岩浆的任意水平交界方块上方为蓝冰, 下方为灵魂土).
* 矿物产生时播放声音(声音可通过 PlaceholderAPI 语法实现随机音调).
* 支持不同世界不同刷石机.
* 矿物概率可任意值, 无需考虑其百分比.
* 配置文件节点均可忽略大小写.
* 插件在 GitHub 以 GPLv3.0 协议开源, 请确保在遵守协议的情况下使用该插件.

***

##### Commands | 命令:
* */ms help* - List all help information | 显示帮助列表
* */ms reload* - Reload configuration file | 重新载入配置文件

***

##### Images | 图片:
<img src="http://mc3.roselle.vip:602/plugins/mineralspawner/images/1.jpg"/>


##### Config | 配置文件:
```
settings:
  # Sound effects played when minerals are generated, support PlaceholderAPI to achieve random tones
  # Format: Sound Name-Volume-Pitch
  # 产生矿物时播放的音效, 支持 PlaceholderAPI 实现随机音调
  # 格式: 声音名称-音量-音调
  spawn-sound: 'ENTITY_ITEM_PICKUP-1.0-1.0'


groups:
    # Ores type and chance
    # Format: material:chance
    # 刷矿种类和概率
    # 格式: material:chance
  - spawns:
      - 'COBBLESTONE:60'
      - 'STONE:20'
      - 'DIORITE:1'
      - 'GRANITE:1'
      - 'ANDESITE:1'
      - 'GRAVEL:5'
      - 'DIRT:5'
      - 'COAL_ORE:10'
      - 'IRON_ORE:10'
      - 'GOLD_ORE:10'
      - 'LAPIS_ORE:5'
      - 'REDSTONE_ORE:20'
      - 'DIAMOND_ORE:15'
      - 'EMERALD_ORE:5'
    # 启用的世界, 指该刷石机仅在以下世界才能发挥作用.
    # The enabled world means that the ore producer can only play a role in the following worlds.
    enabled-world:
      - 'bskyblock_world'
```

***
##### Releases | 发布:
[Click to enter | 点击进入](https://github.com/Score2/MineralSpawner/releases)

***
##### Links | 链接:
[<img src="http://mc3.roselle.vip:602/icons/github.svg" width="64" height="64"/>](https://github.com/Score2/MineralSpawner) 　
[<img src="http://mc3.roselle.vip:602/icons/wiki.svg" width="64" height="64"/>](https://github.com/Score2/MineralSpawner/wiki) 　

> If you have any questions or feature suggestions, please go to [GitHub Issue](https://github.com/Score2/MineralSpawner/issues) for feedback.
>
> 如果有任何问题或者功能建议, 欢迎前往 [GitHub Issue](https://github.com/Score2/MineralSpawner/issues) 反馈