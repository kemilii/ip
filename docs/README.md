# Moli Chatbot User Guide

![Moli Chatbot Screenshot](Interface.png)

Moli Chatbot is your friendly command-line assistant designed to help you manage your tasks. It supports a range of features such as adding tasks, listing them, marking tasks as done or not done, deleting tasks, and finding tasks by keyword. Tasks are saved automatically to disk so you never lose your progress.

## Adding Deadlines

To add a deadline task, use the `deadline` command followed by the task description and the deadline specified with `/by`.  
The deadline must be entered in the format `yyyy-MM-dd HHmm` (for example, `2023-05-15 1400`).

**Example:**
```
deadline return book /by 2023-05-15 1400
```

**Expected output:**
```
Added: [D][ ] return book (by: May 15 2023, 2:00 PM)
✅ Now you have 1 tasks in the list.
```

If the date/time format is incorrect, Moli will display an error message prompting you to use the correct format.

## Adding Todos

To add a todo task (a task without a specific date/time), use the `todo` command followed by the task description.

**Example:**
```
todo read book
```

**Expected output:**
```
Added: [T][ ] read book
✅ Now you have 1 tasks in the list.
```

## Adding Events

To add an event task, use the `event` command followed by the task description, the start time, and the end time. Both times must be in the format `yyyy-MM-dd HHmm`.

**Example:**
```
event project meeting /from 2023-05-15 1400 /to 2023-05-15 1600
```

**Expected output:**
```
Added: [E][ ] project meeting (from: May 15 2023, 2:00 PM to: May 15 2023, 4:00 PM)
✅ Now you have 1 tasks in the list.
```

## Listing Tasks

To view all tasks, simply type:
```
list
```

**Expected output:**
```
Here's what you've told me so far:
1. [T][ ] read book
2. [D][ ] return book (by: May 15 2023, 2:00 PM)
3. [E][ ] project meeting (from: May 15 2023, 2:00 PM to: May 15 2023, 4:00 PM)
```

## Marking Tasks as Done

To mark a task as done, type:
```
mark <task number>
```

**Example:**
```
mark 2
```

**Expected output:**
```
Nice! I've marked this task as done: 
[D][X] return book (by: May 15 2023, 2:00 PM)
Well done! I'm so proud of you!
```

## Unmarking Tasks

To mark a task as not done, type:
```
unmark <task number>
```

**Example:**
```
unmark 2
```

**Expected output:**
```
Okay, I've marked this task as not done yet: 
[D][ ] return book (by: May 15 2023, 2:00 PM)
But I believe you will finish it very soon!
```

## Deleting Tasks

To delete a task, type:
```
delete <task number>
```

**Example:**
```
delete 3
```

**Expected output:**
```
Noted. I've removed this task: 
[E][ ] project meeting (from: May 15 2023, 2:00 PM to: May 15 2023, 4:00 PM)
Now you have 2 tasks in the list.
```

## Finding Tasks

To search for tasks containing a specific keyword in the description, use the `find` command followed by the keyword.

**Example:**
```
find book
```

**Expected output:**
```
Here are the matching tasks in your list:
1. [T][X] read book
2. [D][X] return book (by: May 15 2023, 2:00 PM)
```

## Exiting the Chatbot

To exit the chatbot, simply type:
```
bye
```

**Expected output:**
```
Take care of yourself💖 Come back anytime you need a listening ear.
```

---

Enjoy using Moli Chatbot to manage your tasks efficiently!