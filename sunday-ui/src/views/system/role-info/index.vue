<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-collapse value="1">
      <el-collapse-item title="" name="1">
        <el-form :inline="true" :model="searchForm" ref="searchForm">
          <el-form-item label="角色" prop="name">
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
      </el-collapse-item>
    </el-collapse>
    <!-- 操作按钮 -->
    <div class="button-container" ref="button-container">
      <el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
    </div>
    <!-- 数据表格 -->
    <el-table
      :data="tableData"
      highlight-current-row
      :header-cell-style="{background:'#eef1f6', color:'#606266'}">
      <el-table-column
        label="序号"
        type="index"
        width="50">
      </el-table-column>
      <el-table-column
        label="角色">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="描述">
        <template slot-scope="scope">
          <p>{{scope.row.description}}</p>
        </template>
      </el-table-column>
      <el-table-column
        prop="sort"
        sortable
        label="排序">
      </el-table-column>
      <el-table-column
        label="创建时间" width="140">
        <template slot-scope="scope">
          <p>{{scope.row.createTime}}</p>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" fixed="right" width="140">
        <template slot-scope="scope">
          <el-button
            type="text"
            icon="el-icon-edit"
            @click="handleEdit(scope.row)">
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
    <!-- 分页插件 -->
    <pagination v-if="tableDataCount > 0"
                :total="tableDataCount"
                :page.sync="searchForm.page"
                :limit.sync="searchForm.limit"
                @pagination="handlePagination"/>
    <!-- 新增或更新角色 -->
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
        <el-form-item label="排序" prop="sort">
          <el-input type="number" v-model="dataForm.sort" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="resourceIds">
          <el-tree
            :data="treeData"
            show-checkbox
            node-key="id"
            ref="tree"
            highlight-current
            :props="treeProps"
            :check-strictly="treeCheckStrictly">
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
        // 查询条件
        searchForm: {
          name: '',
          page: 0,
          limit: 10
        },
        // 表格数据
        tableData: [],
        tableDataCount: 0,
        // 树配置
        treeProps: {
          children: 'children',
          label: 'name'
        },
        treeData: [],
        treeCheckStrictly: false,
        // 弹框
        showDialogTitle: '',
        showDialogVisible: false,
        deleteVisible: false,
        // 表单数据
        dataForm: {
          id: 0,
          name: '',
          sort: 0,
          description: '',
          resourceIds: ''
        },
        // 表单规则
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
        this.$nextTick(() => {
          this.$refs.dataForm.resetFields()
        });
        this.showDialogTitle = '新增角色'
        this.showDialogVisible = true
        this.treeCheckStrictly = false
      },
      // 编辑按钮
      handleEdit(row) {
        this.showDialogTitle = '编辑角色'
        this.showDialogVisible = true
        this.treeCheckStrictly = true
        this.dataForm.id = row
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
