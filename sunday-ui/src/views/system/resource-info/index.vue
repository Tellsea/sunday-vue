<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-form :inline="true" :model="searchForm" ref="searchForm">
      <el-form-item label="菜单名称" prop="name">
        <el-input v-model="searchForm.name" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="loadTable" title="查询">
          查询
        </el-button>
        <el-button icon="el-icon-refresh-left" @click="handleReset" title="重置">
          重置
        </el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table
      :data="tableData"
      row-key="id"
      highlight-current-row
      :header-cell-style="{background:'#eef1f6', color:'#606266'}"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column
        prop="name"
        label="菜单名称">
      </el-table-column>
      <el-table-column
        label="菜单图标"
        align="center"
        width="100">
        <template slot-scope="scope">
          <svg-icon v-if="scope.row.icon" :icon-class="scope.row.icon"/>
        </template>
      </el-table-column>
      <el-table-column
        prop="url"
        label="路由地址">
      </el-table-column>
      <el-table-column
        prop="component"
        label="组件地址">
      </el-table-column>
      <el-table-column
        prop="perms"
        label="权限标识" width="150">
      </el-table-column>
      <el-table-column
        label="类型" width="100">
        <template slot-scope="scope">
          <div v-if="scope.row.type == 0">未使用</div>
          <div v-if="scope.row.type == 1">菜单</div>
          <div v-if="scope.row.type == 2">按钮</div>
          <div v-if="scope.row.type == 3">链接</div>
        </template>
      </el-table-column>
      <el-table-column
        prop="sort"
        sortable
        label="排序" width="100">
      </el-table-column>
      <el-table-column label="操作" align="center" fixed="right" width="200">
        <template slot-scope="scope">
          <el-button
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)">
            新增
          </el-button>
          <el-button
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)">
            修改
          </el-button>
          <el-button
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增或编辑菜单 -->
    <el-dialog
      :title="showDialogTitle"
      :visible.sync="showDialogVisible"
      @close="closeDialog"
      width="500px">
      <el-form :model="dataForm" :rules="rules" ref="dataForm" label-width="100px">
        <el-form-item label="权限名称" prop="name">
          <el-input v-model="dataForm.name" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="权限标识" prop="perms">
          <el-input v-model="dataForm.perms"
                    placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="权限类型" prop="type">
          <el-radio-group v-model="dataForm.type">
            <el-radio :label="1">菜单</el-radio>
            <el-radio :label="2">按钮</el-radio>
            <el-radio :label="3">链接</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="路由地址" prop="url">
          <el-input v-model="dataForm.url" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="组件地址" prop="component">
          <el-input v-model="dataForm.component" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="dataForm.icon" placeholder="请输入"
                    suffix-icon="dataForm.icon"></el-input>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input type="number" v-model="dataForm.sort" placeholder="请输入"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="showDialogVisible=false">取消</el-button>
          <el-button type="primary" :loading="submitLoading" @click="handleSubmit">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        // 查询条件
        searchForm: {
          name: ''
        },
        // 表格数据
        tableData: [],
        // 表单
        dataForm: {
          id: 0,
          pid: 0,
          name: '',
          url: '',
          component: '',
          perms: '',
          type: 1,
          icon: '',
          sort: 0,
        },
        // 表单规则
        rules: {
          name: [{required: true, message: '请输入', trigger: 'blur'}],
          perms: [{required: true, message: '请输入', trigger: 'blur'}],
        },
        // 对话框标题
        showDialogTitle: '',
        // 对话框是否显示
        showDialogVisible: false,
        // 提交按钮
        submitLoading: false
      }
    },
    methods: {
      // 加载表格
      loadTable() {
        this.$api.resourceInfo.listByTree(this.searchForm).then(res => {
          this.tableData = res.data
        })
      },
      // 重置
      handleReset() {
        this.$refs['searchForm'].resetFields()
        this.loadTable()
      },
      // 关闭对话框
      closeDialog() {
        this.$nextTick(() => {
          if (this.$refs.dataForm !== undefined) {
            this.$refs.dataForm.resetFields()
          }
        })
        this.submitLoading = false
      },
      // 新增按钮
      handleAdd(row) {
        this.$nextTick(() => {
          if (this.$refs.dataForm !== undefined) {
            this.$refs.dataForm.resetFields()
          }
        })
        this.dataForm.pid = row.id
        this.showDialogTitle = '新增菜单'
        this.showDialogVisible = true
      },
      // 编辑按钮
      handleUpdate(row) {
        this.showDialogTitle = '编辑菜单'
        this.showDialogVisible = true
        this.copyAttrToLeft(this.dataForm, row)
      },
      // 新增/更新
      handleSubmit() {
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            this.submitLoading = true
            if (this.dataForm.id == 0) {
              this.$api.resourceInfo.save(this.dataForm).then(res => {
                this.$message.success({
                  message: res.message,
                  onClose: () => {
                    this.showDialogVisible = false
                    this.loadTable()
                  }
                })
              })
            } else {
              this.$api.resourceInfo.update(this.dataForm).then(res => {
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
      this.loadTable()
    }
  }
</script>

<style scoped>

</style>
