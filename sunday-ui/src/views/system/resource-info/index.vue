<template>
  <div class="app-container">
    <el-row>
      <el-col :md="10" :sm="24">
        <el-button type="primary" @click="treeAppendRoot">
          新增一级菜单
        </el-button>
        <el-button type="primary" @click="treeExpandChange(true)">
          展开全部
        </el-button>
        <el-button type="primary" @click="treeExpandChange(false)">
          折叠全部
        </el-button>
        <div class="tree-scroll-bar">
          <el-tree
            :data="treeData"
            node-key="id"
            ref="tree"
            :default-expand-all="treeExpandAll"
            :expand-on-click-node="false"
            :props="treeProps"
            @node-click="treeNodeClick">
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}</span>
            <span>
              <el-button
                type="text"
                size="mini"
                @click="() => treeAppend(data)">
                添加
              </el-button>
              <el-button
                type="text"
                size="mini"
                @click="() => treeRemove(node, data)">
                删除
              </el-button>
            </span>
          </span>
          </el-tree>
        </div>
      </el-col>
      <el-col :md="14" :sm="24">
        <div class="app-roll-fix">
          <el-form :model="dataForm" :rules="rules" ref="dataForm" label-width="100px">
            <el-form-item label="名称" prop="name">
              <el-input v-model="dataForm.name" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item label="权限" prop="perms">
              <el-input v-model="dataForm.perms"
                        placeholder="例如：add、update、delete、select、detail)"></el-input>
            </el-form-item>
            <el-form-item label="类型" prop="type">
              <el-radio-group v-model="dataForm.type">
                <el-radio label="0">未使用</el-radio>
                <el-radio label="1">菜单</el-radio>
                <el-radio label="2">按钮</el-radio>
                <el-radio label="3">链接</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="路径" prop="url">
              <el-input v-model="dataForm.url" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item label="图标" prop="icon">
              <el-input v-model="dataForm.icon" placeholder="请输入"
                        suffix-icon="dataForm.icon"></el-input>
            </el-form-item>
            <el-form-item label="排序" prop="sort">
              <el-input type="number" v-model="dataForm.sort" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" :loading="submitLoading" @click="submitForm">保存
              </el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "resourceInfoList",
    data() {
      return {
        submitLoading: false,
        treeData: [],
        dataForm: {
          id: 0,
          name: '',
          url: '',
          perms: '',
          type: '0',
          icon: '',
          sort: 0,
        },
        rules: {
          name: [{required: true, message: '请输入', trigger: 'blur'}],
          perms: [{required: true, message: '请输入', trigger: 'blur'}],
        },
        treeExpandAll: true,
        treeProps: {
          children: 'children',
          label: 'name'
        }
      }
    },
    methods: {
      // 树添加根节点
      treeAppendRoot() {
        let param = {
          name: '(空)',
          perms: 'test'
        }
        this.$api.resourceInfo.save(param).then(res => {
          this.$message.success({
            message: res.message,
            onClose: () => {
              this.loadResourceTree()
            }
          })
        })
      },
      // 树添加
      treeAppend(data) {
        let param = {
          pid: data.id,
          name: '(空)',
          perms: 'test'
        }
        this.$api.resourceInfo.save(param).then(res => {
          this.$message.success({
            message: res.message,
            onClose: () => {
              this.loadResourceTree()
            }
          })
        })
      },
      // 树删除
      treeRemove(node, data) {
        const parent = node.parent;
        const children = parent.data.children || parent.data;
        const index = children.findIndex(d => d.id === data.id);
        children.splice(index, 1);
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          this.$api.resourceInfo.deleteById({id: data.id}).then(res => {
            this.$message.success({
              message: res.message,
              onClose: () => {
                this.loadResourceTree()
              }
            })
          })
        })
      },
      // 树点击节点
      treeNodeClick(data, node) {
        data.type = String(data.type)
        this.dataForm = data
      },
      // 树修改展开状态
      treeExpandChange(status) {
        this.treeExpandAll = status
        for (let j = 0; j < this.$refs.tree.store._getAllNodes().length; j++) {
          this.$refs.tree.store._getAllNodes()[j].expanded = this.treeExpandAll
        }
      },
      // 表单提交
      submitForm() {
        if (this.dataForm.id === 0) {
          this.$message.error('请选择左侧节点')
          return false
        }
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            this.submitLoading = true
            this.$api.resourceInfo.update(this.dataForm).then(res => {
              this.$message.success({
                message: res.message,
                onClose: () => {
                  this.submitLoading = false
                }
              })
            })
          } else {
            this.$message.error('请完善表单信息')
            return false
          }
        });
      },
      // 设置树选中
      setTreeSelect(data) {
        this.$nextTick(() => {
          this.$refs.tree.setCheckedKeys(data);
        });
      },
      // 加载资源树
      loadResourceTree() {
        this.$api.resourceInfo.listByTree().then(res => {
          this.treeData = res.data
        })
      }
    },
    mounted() {
      this.loadResourceTree()
    }
  }
</script>

<style scoped>
  .tree-scroll-bar {
    margin-top: 20px;
    height: calc(100vh - 156px);
    overflow: auto;
  }

  /*树按钮*/
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
</style>
