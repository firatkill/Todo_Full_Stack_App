<template>
  <div v-if="isAddTodoModal" @click="closeModal" class="AddTodoModal">
    <div @click="(e) => e.stopPropagation()" class="modalContainer">
      <h1 style="text-align: center; align-self: center" class="">Add Todo</h1>
      <hr />
      <label>Todo Name</label>
      <input v-model="todo.todoName" class="addTodoInput" type="text" />
      <label>Todo Description</label>
      <input v-model="todo.todoDescription" class="addTodoInput" type="text" />
      <label>Todo Date</label>
      <input v-model="todo.date" class="addTodoInput" type="datetime-local" />
      <label>Todo Category</label>
      <select v-model="todo.todoCategory" class="addTodoInput">
        <option
          v-for="category in todoCategories"
          :key="category.id"
          :value="category.categoryName"
          :selected="currentCategory.categoryName == category.categoryName"
        >
          {{ category.categoryName }}
        </option>
      </select>
      <label>Pick a color for todo:</label>

      <select v-model="todo.todoColor" class="addTodoInput">
        <option
          v-for="colorOption in colorOptions"
          :key="colorOption.id"
          :value="colorOption.colorName"
        >
          {{ colorOption.colorName }}
        </option>
      </select>

      <button @click="submitHandler" class="addTodoInput">Add Todo</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddTodoModal",
  data() {
    return {
      todo: {
        todoName: "",
        todoDescription: "",
        todoColor: "",
        todoCategory: "",
        date: "",
      },
      colorOptions: [
        { id: 0, colorName: "green" },
        { id: 1, colorName: "yellow" },
        { id: 2, colorName: "blue" },
        { id: 3, colorName: "orange" },
        { id: 4, colorName: "red" },
      ],
    };
  },
  methods: {
    closeModal() {
      this.$store.dispatch("toggleModal", false);
    },
    colorHandler(e) {
      this.todo.todoColor = e.target.value;
    },
    selectHandler(e) {
      this.todo.todoCategory = e.target.value;
    },
    submitHandler() {
      this.$store.dispatch("postTodo", this.todo);
    },
  },
  computed: {
    isAddTodoModal() {
      return this.$store.state.todo.isAddTodoModal;
    },
    todoCategories() {
      return this.$store.state.todo.categories.filter(
        (category) => category.categoryName !== "All"
      );
    },
    currentCategory() {
      return this.$store.state.todo.currentCategory;
    },
  },
};
</script>

<style scoped>
.AddTodoModal {
  position: absolute;
  z-index: 1000;
  width: 100%;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}
.modalContainer {
  display: flex;
  flex-direction: column;
  align-items: flex-start;

  border-radius: 20px;
  width: 50vw;
  min-width: 500px;
  background-color: white;
  padding: 10px;
  padding-right: 0rem;
}
label {
  margin-left: 5%;
}
.addTodoInput {
  width: 90%;
  align-self: center;
  height: 3rem;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 8px;
  border: none;
  outline: 5px solid transparent;
  font-size: 1.2rem;

  padding-left: 1rem;

  background-color: rgba(234, 89, 89, 0.75) !important;
  margin-bottom: 1rem;
  transition: box-shadow, outline 0.5s ease-in-out;
}
.addTodoInput:hover {
  background-color: transparent !important;
  color: rgb(234, 89, 89) !important;
  border: 1px solid rgb(234, 89, 89) !important;
}
.addTodoInput:focus {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.5);
  outline: 5px solid rgba(0, 0, 0, 0.05);
}
.modalContainer button {
  width: 45%;
  background-color: rgb(162, 200, 162) !important;
  align-self: center;
}
</style>
