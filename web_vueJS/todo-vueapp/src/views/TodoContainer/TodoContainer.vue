<template>
  <div :key="re_render_key" class="TodoContainer">
    <h1 style="font-size: 3rem; color: #ea5959">
      {{ category.categoryName ? category.categoryName : "All" }}
    </h1>
    <div class="inputContainer">
      <button @click="openAddTodoModal" class="addTodoInput">
        Add a new task inside {{ category.categoryName }} category
      </button>
      <button @click="clearCompletedTodos">Clear Completed</button>
    </div>
    <div class="list-group mt-3">
      <TodoListItem
        @checkTodo="checkTodo"
        @deleteTodo="deleteTodo"
        v-for="todo in todos"
        :key="todo.id"
        :todo="todo"
      />
    </div>
  </div>
</template>

<script>
import TodoListItem from "@/components/TodoContainer/TodoListItem.vue";

export default {
  name: "TodoContainer",
  data() {
    return {
      newTodo: "",
      re_render_key: 0,
    };
  },
  methods: {
    clearCompletedTodos() {
      if (confirm("Are you sure you want to delete all completed todos?")) {
        const todoIds = [];
        this.todos
          .filter((todo) => todo.completed == true)
          .forEach((todo) => todoIds.push(todo.id));
        this.$store.dispatch("deleteMultipleTodos", todoIds);
      }
    },
    addTodo() {
      this.$store.dispatch("addTodo", {
        name: this.newTodo,
        caption: "Check in with mom and catch up on news",
        category: this.category,
        isCompleted: false,
      });
      this.newTodo = "";
    },
    openAddTodoModal() {
      this.$store.dispatch("toggleModal", true);
    },
    checkTodo(todo) {
      this.$store.dispatch("updateTodo", todo);
      this.re_render_key += 1;
    },
    deleteTodo(todo) {
      this.$store.dispatch("deleteTodo", todo.id);
      this.re_render_key += 1;
    },
  },
  computed: {
    category() {
      return this.$store.state.todo.currentCategory;
    },
    todos() {
      let todos = this.$store.state.todo.currentTodos;

      todos = todos.sort((a, b) => a.date.localeCompare(b.date));

      if (this.category.id === 0 || !this.category.categoryName) {
        return todos;
      } else {
        todos = todos.filter(
          (todo) => todo.todoCategory.id == this.category.id
        );
        return todos;
      }
    },
  },
  components: { TodoListItem },
  mounted() {
    this.$store.dispatch("getAllTodos");
    this.$store.dispatch("getAllCategories");
  },
};
</script>

<style scoped>
.TodoContainer {
  max-width: 1000px;
  width: 95%;
  height: 100%;
  display: flex;

  flex-direction: column;
}
.inputContainer {
  display: flex;
  justify-content: space-between;
}
.inputContainer button {
  height: 3rem;
  align-self: flex-end;
  padding: 0rem 1rem;
  border-radius: 8px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  background-color: rgb(234, 89, 89);
  color: white;
  border: none;
  transition: all 0.15s ease-out;
}
.inputContainer button:hover {
  color: rgb(234, 89, 89);
  border: 1px solid rgb(234, 89, 89);
  background-color: transparent;
}

.addTodoInput {
  width: 80%;
  height: 3rem;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 8px;
  border: none;
  outline: 5px solid transparent;
  font-size: 1.2rem;
  padding-left: 1rem;
  background-color: rgb(162, 200, 162) !important;
  margin-top: 1rem;
  transition: box-shadow, outline 0.5s ease-in-out;
}
.addTodoInput:hover {
  background-color: transparent !important;
  color: rgb(162, 200, 162) !important;
  border: 1px solid rgb(162, 200, 162) !important;
}
.addTodoInput:focus {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.5);
  outline: 5px solid rgba(0, 0, 0, 0.05);
}
.list-group {
  overflow: scroll;
  flex: 1;
}
</style>
