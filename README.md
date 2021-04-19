# Cube 30 Touch Transmission mode

A transmission mode provider for [Diesse Cube 30 Touch](https://www.streck.com/products/sed-rate/diesse-cube-30-touch/) erythrocyte sedimentation rate analyzer

---

## Installation
Like any other Mirth plugin
1. Drop zip contents into `mirthroot/extensions`
   - **OR**
2. Install through Mirth Connect Administrator
---

## Usage
1. Set Source Connector inbound datatype to `XML`
1. Set Source Connector Type to `TCP Listener`
1. Set transmission mode to `Cube30Touch Transmission Mode`
1. Set TCP Listener data type to `Text` with default encoding. UTF-8 should be fine.

---
## Todo
* More simplifycation
* Implement more stuff from the specification
* Also should probably definitely move to byte iteration for field values instead of the current Regex... 

---
## Notes
- This plugin does not do everything in the specification! As it stands right now it just receives messages and makes a pretty XML of it. The main purpose of this plugin was to simplify checksum calculation.  

---
## Contributing
**Pull requests are always welcome**

---
## Screenshots
##### 1. Datatypes
![](screenshots/datatypes.png)

##### 2. Source connector settings
![](screenshots/source_connector.png)
