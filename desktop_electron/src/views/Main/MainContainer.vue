<template>
  <div class="MainContainer ps-5 pt-5">
    <TodoContainer />
  </div>
</template>

<script>
import TodoContainer from "../TodoContainer/TodoContainer.vue";
export default {
  name: "MainContainer",
  components: { TodoContainer },
};
</script>

<style>
.MainContainer {
  flex: 1;
}
</style>
