exports.install = function (Vue, options) {
  // 复制右侧的属性到左侧
  Vue.prototype.copyAttrToLeft = function (sourceObj, targetObj) {
    if (sourceObj.constructor === Object && targetObj.constructor === Object) {
      for (let attr in sourceObj) {
        sourceObj[attr] = targetObj[attr]
      }
      return sourceObj;
    }
  }
}
