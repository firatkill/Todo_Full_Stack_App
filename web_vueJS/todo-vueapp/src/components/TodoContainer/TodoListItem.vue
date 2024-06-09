<template>
  <div class="list-item py-2 d-flex align-items-center justify-content-between">
    <div
      style="max-width: 70%; overflow: hidden"
      class="d-flex align-items-center"
    >
      <div
        @click="$emit('checkTodo', todo)"
        class="checkButton d-flex align-items-center justify-content-center me-2"
      >
        <img v-show="currentTodo.completed" src="@/assets/checkIcon.svg" />
        <img
          v-if="!currentTodo.completed"
          style="display: none; opacity: 0.7"
          class="checkButtonImg"
          src="@/assets/checkIcon.svg"
        />
      </div>
      <div class="d-flex flex-column">
        <p
          style="flex-shrink: 0; font-size: 1.25rem"
          :style="
            currentTodo.completed &&
            'text-decoration-line:line-through;opacity:0.5'
          "
          class="p-0 m-0"
        >
          {{ currentTodo.todoName }}
        </p>
        <p
          style="flex-shrink: 0"
          :style="
            currentTodo.completed &&
            'text-decoration-line:line-through;opacity:0.5'
          "
          class="p-0 m-0"
        >
          {{ currentTodo.todoDescription }}
        </p>
      </div>
    </div>
    <div class="d-flex align-items-center">
      <span
        :style="{
          backgroundColor: currentTodo.todoColor,
          opacity: currentTodo.completed && '.5',
        }"
        class="categorySpan text-white me-4"
        style="height: 2rem"
        >{{ new Date(currentTodo.date).toLocaleString() }}</span
      >
      <img
        @click="$emit('deleteTodo', todo)"
        class="trashBin"
        src="@/assets/trashBin.svg"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "TodoListGroup",
  props: ["todo"],
  data() {
    return {
      currentTodo: this.todo,
    };
  },
  methods: {
    deleteTodo(id) {
      if (confirm("Are you sure you want to delete todo?")) {
        this.$store.dispatch("deleteTodo", { id: id });
      }
    },
  },
};
</script>

<style scoped>
.list-item {
  border-bottom: 1px solid black;
  width: 100%;
  padding: 25px 10px !important;
}
.checkButton {
  border: 2px solid #ea5959;
  width: 28px;
  height: 28px;
  border-radius: 6px;
  cursor: pointer;
  flex-shrink: 0;
}
.checkButton:hover > .checkButtonImg {
  display: block !important;
}
.categorySpan {
  padding: 5px 10px;
  border-radius: 6px;
}
.trashBin:hover {
  cursor: pointer;
  transform: scale(1.05);
}
</style>
