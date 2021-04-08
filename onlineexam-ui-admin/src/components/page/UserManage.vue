<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 用户管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box" align="center">
                <el-input v-model="searchKey" placeholder="用户名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
            <el-table
                :data="user"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
              <el-table-column type="selection" width="55" align="center"></el-table-column>
              <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column>
              <el-table-column prop="username" label="用户名"></el-table-column>
              <el-table-column prop="perm" label="权限"></el-table-column>
              <el-table-column prop="email" label="邮箱"></el-table-column>
              <el-table-column prop="device" label="登录设备"></el-table-column>
              <el-table-column prop="os" label="登录系统" align="center"></el-table-column>
              <el-table-column prop="ip" label="ip"></el-table-column>
              <el-table-column prop="address" label="地址"></el-table-column>
              <el-table-column prop="browser" label="登录浏览器" align="center"></el-table-column>
              <el-table-column prop="register_date" label="注册时间"></el-table-column>
              <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)"
                        >编辑</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
              <el-pagination style="margin-top: 2%"
                             background
                             layout="prev, pager, next"
                             page-size=4
                             :total="total"
                             @current-change="page">
              </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="用户名">
                    <el-input v-model="name"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: 'userManage',
    data() {
        return {
          total: 0,
          searchKey: '',
          user: {
            id: 0,
            email: '',
            password: '',
            username: '',
            ip: '',
            os: '',
            address: '',
            browser: '',
            device: '',
            perm: '',
            register_date:''
          },
            query: {
                address: '',
                name: '',
                pageIndex: 1,
                pageSize: 5
            },
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            form: {},
          name: '',
            idx: -1,
            id: -1
        };
    },
    created() {
        this.getData();
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
          const _this = this
          axios.get('http://localhost:83/information/countAllUsers').then(function (resp) {
            _this.total = resp.data['data']
            console.log(resp.data)
          })
          axios.get('http://localhost:83/information/showAllUsers/1/4').then(function (resp) {
            _this.user = resp.data['data']
          })
        },
        // 触发搜索按钮
        handleSearch() {
          if (this.searchKey!==''){
            const _this = this
            axios.get('http://localhost:83/information/searchUserInfo/'+this.searchKey+"/"+1+"/"+4).then(function (resp) {
              _this.total = Number(resp.data['message'])
              _this.user=resp.data['data']
            })
          }else {
            console.log("搜索为空")
            const _this = this
            axios.get('http://localhost:83/information/countAllUsers').then(function (resp) {
              _this.total = resp.data['data']
              console.log(resp.data)
            })
            axios.get('http://localhost:83/information/showAllUsers/1/4').then(function (resp) {
              _this.user = resp.data['data']
            })
        }
        },

        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    this.$message.success('删除成功');
                    this.tableData.splice(index, 1);
                })
                .catch(() => {});
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        delAllSelection() {
            const length = this.multipleSelection.length;
            let str = '';
            this.delList = this.delList.concat(this.multipleSelection);
            for (let i = 0; i < length; i++) {
                str += this.multipleSelection[i].name + ' ';
            }
            this.$message.error(`删除了${str}`);
            this.multipleSelection = [];
        },
        // 编辑操作
        handleEdit(index, row) {
            this.idx = index;
            this.form = row;
            this.editVisible = true;

        },
        // 保存编辑
        saveEdit() {
          console.log("youxiang a "+this.form.email)
          console.log(this.name)
          const _this = this
          axios.get('http://localhost:83/information/update/'+this.name+"/"+this.form.email).then(function (resp) {
            console.log(resp.data)
          })
            this.editVisible = false;

        },
      page(currentPage) {
        const _this = this
        axios.get('http://localhost:83/information/countAllUsers').then(function (resp) {
          _this.total = resp.data['data']
          console.log(resp.data)
        })
        axios.get('http://localhost:83/information/showAllUsers/'+currentPage+'/4').then(function (resp) {
          _this.user = resp.data['data']
        })
      },
    }
}
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
