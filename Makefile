COMPILER = javac
SRCS = astar_search.java \
colors.java \
IO.java \
node.java \
program.java \

CLASSES = astar_search.class \
colors.class \
compare_f_value.class \
IO.class \
node.class \
program.class

RED = \033[31m
GRN = \033[32m
CYN = \033[36m
CLR = \033[0m

all:
	@$(COMPILER) $(SRCS)
	@echo "$(GRN)Program compiled$(CLR)"
	@echo "$(CYN)usage:\n\tjava program <heuristic> <input file>$(CLR)"

fclean:
	@rm $(CLASSES);
	@echo "$(RED)removed all class files$(CLR)"

re : fclean all