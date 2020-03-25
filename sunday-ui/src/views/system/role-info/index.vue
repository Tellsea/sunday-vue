<template>
  <div class="app-container">
    <el-collapse value="1">
      <el-collapse-item title="" name="1">
        <el-form :inline="true" :model="searchForm" ref="searchForm">
          <el-form-item label="角色" prop="name">
            <el-input v-model="searchForm.name" placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="loadTable" title="查询">
            </el-button>
            <el-button icon="el-icon-refresh-left" @click="handleReset" title="重置">
            </el-button>
          </el-form-item>
        </el-form>
      </el-collapse-item>
    </el-collapse>
    <el-button type="primary" @click="handleAdd">新增</el-button>
    <el-table stripe
              :data="tableData"
              :highlight-current-row="true"
              :header-cell-style="{background:'#eef1f6', color:'#606266'}">
      <el-table-column
        label="序号"
        type="index"
        width="50">
      </el-table-column>
      <el-table-column
        label="角色" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="描述" width="100">
        <template slot-scope="scope">
          <p>{{scope.row.description}}</p>
        </template>
      </el-table-column>
      <el-table-column
        label="权限">
        <template slot-scope="scope">
          <p>{{scope.row.resourceName}}</p>
        </template>
      </el-table-column>
      <el-table-column fixed="right" width="120" label="操作">
        <template slot-scope="scope">
          <i class="el-icon-edit el-icon-blue" title="编辑" @click="handleEdit(scope.$index, scope.row)"></i>
          <i class="el-icon-delete el-icon-red" title="删除" @click="handleDelete(scope.$index, scope.row)"></i>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-if="tableDataCount > 0"
                :total="tableDataCount"
                :page.sync="searchForm.page"
                :limit.sync="searchForm.limit"
                @pagination="handlePagination"/>
    <el-dialog
      :title="showDialogTitle"
      :visible.sync="showDialogVisible"
      @close="closeDialog"
      width="500px">
      <el-form :model="dataForm" :rules="rules" ref="dataForm" label-width="100px"
               class="demo-ruleForm">
        <el-form-item label="名称" prop="name">
          <el-input v-model="dataForm.name" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="dataForm.description" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="resourceIds">
          <el-tree
            :data="treeData"
            show-checkbox
            default-expand-all
            node-key="id"
            ref="tree"
            highlight-current
            :props="treeProps"
            check-strictly>
          </el-tree>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="showDialogVisible=false">关闭</el-button>
                <el-button type="primary" :loading="submitLoading" @click="handleSubmit">确定</el-button>
            </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        searchForm: {
          name: '',
          page: 0,
          limit: 10
        },
        tableData: [],
        tableDataCount: 0,
        treeProps: {
          children: 'children',
          label: 'name'
        },
        treeData: [],
        showDialogTitle: '',
        showDialogVisible: false,
        deleteVisible: false,
        dataForm: {
          id: 0,
          name: '',
          description: '',
          resourceIds: ''
        },
        rules: {
          name: [{required: true, message: '请输入', trigger: 'blur'}],
          resourceIds: [{required: true, message: '请选择权限', trigger: 'blur'}]
        },
        submitLoading: false
      }
    },
    methods: {
      // 加载表格数据
      loadTable() {
        this.$api.roleInfo.listByTable(this.searchForm).then(res => {
          this.tableDataCount = res.count
          this.tableData = res.data
        })
      },
      // 处理分页
      handlePagination(data) {
        this.searchForm.page = data.page
        this.searchForm.limit = data.limit
        this.loadTable()
      },
      // 重置
      handleReset() {
        this.$refs['searchForm'].resetFields()
        this.searchForm.page = 0
        this.searchForm.limit = 10
        this.loadTable()
      },
      // 加载资源树
      loadResourceTree() {
        this.$api.resourceInfo.listByTree().then(res => {
          this.treeData = res.data
        })
      },
      // 设置树选中
      setTreeSelect(data) {
        this.$nextTick(() => {
          this.$refs.tree.setCheckedKeys(data)
        });
      },
      // 关闭对话框
      closeDialog() {
        this.$nextTick(() => {
          this.$refs.dataForm.resetFields()
        });
        this.$refs.tree.setCheckedKeys([])
        this.submitLoading = false
      },
      // 新增按钮
      handleAdd() {
        this.showDialogTitle = '新增角色'
        this.showDialogVisible = true
      },
      // 编辑按钮
      handleEdit(index, row) {
        this.showDialogTitle = '编辑角色'
        this.showDialogVisible = true
        this.dataForm.id = row.id
        this.dataForm.name = row.name
        this.dataForm.description = row.description
        if (row.resourceIds) {
          this.setTreeSelect(row.resourceIds.split(','))
        }
      },
      // 新增/更新
      handleSubmit() {
        this.dataForm.resourceIds = this.$refs.tree.getCheckedKeys().join(',')
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            this.submitLoading = true
            if (this.dataForm.id == 0) {
              this.$api.roleInfo.save(this.dataForm).then(res => {
                this.$message.success({
                  message: res.message,
                  onClose: () => {
                    this.showDialogVisible = false
                    this.loadTable()
                  }
                })
              })
            } else {
              this.$api.roleInfo.update(this.dataForm).then(res => {
                this.$message.success({
                  message: res.message,
                  onClose: () => {
                    this.showDialogVisible = false
                    this.loadTable()
                  }
                })
              })
            }
          } else {
            this.$message.error('请完善表单信息')
            return false
          }
        });
      },
      // 删除
      handleDelete(index, row) {
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          let param = {
            id: row.id
          };
          this.$api.roleInfo.deleteById(param).then(res => {
            this.$message.success({
              message: res.message,
              onClose: () => {
                this.loadTable();
              }
            })
          })
        });
      }
    },
    mounted() {
      this.loadResourceTree()
      this.loadTable()
    }
  }
</script>

<style scoped>

</style>
