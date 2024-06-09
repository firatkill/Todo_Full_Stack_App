<template>
  <div
    class="position-relative d-flex flex-column justify-content-center"
    style="height: 50%"
  >
    <input
      style="
        font-size: 1.3rem;
        padding: 5px;
        border-radius: 8px;
        margin-bottom: 1rem;
      "
      v-model="categorySearch"
      type="text"
      placeholder="Search.."
    />
    <div
      class="list-group"
      style="
        font-size: 1.2rem;
        font-weight: 700;

        border-radius: 20px;

        padding-left: 0.5rem;
        overflow-y: scroll;
        overflow-x: hidden;
      "
    >
      <div
        class="listItem"
        @click="changeCategory(category)"
        v-for="category in todoCategories"
        :key="category.id"
      >
        {{ category.categoryName }}
      </div>
    </div>
    <div
      @click="addInputSwitch"
      style="font-weight: 700; font-size: 1.3rem; color: #ea5959"
      class="addButton mt-1 ms-1"
    >
      <img class="pe-2" src="@/assets/plusCircle.svg" />add new
    </div>
    <input
      v-show="isAddInput"
      v-model="todoCategory"
      @keypress="addTodoCategory"
      class="addCategoryInput ms-1"
      type="text"
    />
  </div>
</template>

<script>
export default {
  name: "SideBarCategories",
  data() {
    return {
      categorySearch: "",
      isAddInput: false,
      todoCategory: "",
    };
  },
  computed: {
    todoCategories() {
      let categories = this.$store.state.todo.categories;
      if (this.categorySearch.trim() == "") {
        return categories.sort((a, b) =>
          a.categoryName
            .toLowerCase()
            .localeCompare(b.categoryName.toLowerCase())
        );
      } else {
        return categories
          .filter((category) =>
            category.categoryName
              .toLowerCase()
              .includes(this.categorySearch.toLowerCase())
          )
          .sort((a, b) =>
            a.categoryName
              .toLowerCase()
              .localeCompare(b.categoryName.toLowerCase())
          );
      }
    },
  },
  methods: {
    resetCategories() {
      this.filteredCategories = this.todoCategories.slice(0);
      this.categorySearch = "";
    },
    changeCategory(category) {
      this.$store.dispatch("changeCategory", category);
    },
    addInputSwitch() {
      this.isAddInput = !this.isAddInput;
    },
    addTodoCategory(e) {
      if (
        e.keyCode == 13 &&
        this.todoCategory.trim() != "" &&
        this.todoCategory.length >= 3
      ) {
        if (confirm(`Do you want to add ${this.todoCategory} category?`)) {
          this.$store.dispatch("postTodoCategory", this.todoCategory);
        }
      }
    },
  },
};
</script>

<style scoped>
.list-group:hover {
  box-shadow: 0px 0px 5px 5px rgba(0, 0, 0, 0.03);
}

.addButton:hover {
  opacity: 0.75;
  cursor: pointer;
}
.listItem:hover {
  cursor: pointer;
  transform: scaleX(1.05);
  transform-origin: left;
  transition: transform 0.5s ease-out;
  color: #ea5959;
}
.addCategoryInput {
  position: absolute;
  bottom: -2.5rem;
  border-radius: 6px;

  width: 100%;

  border: none;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.5);
  outline: 5px solid rgba(0, 0, 0, 0.05);
  padding: 5px 10px;
}
</style>
