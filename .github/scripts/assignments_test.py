import os, re
from shutil import copyfile

def get_directories(basedir):
    return [os.path.join(basedir, dir) for dir in os.listdir(basedir) \
                                       if os.path.isdir(os.path.join(basedir, dir)) \
                                       and not dir.startswith('.')]

pipeline_failed = False
for category_dir in get_directories(os.getcwd()):
    for assignment_dir in get_directories(category_dir):
        # Change to the assignment directory.
        os.chdir(assignment_dir)

        # Copy the solution to the correct folder.
        testfile = os.listdir(os.path.join(assignment_dir, 'solution'))[0]
        copyfile(f'{assignment_dir}/solution/{testfile}', f'{assignment_dir}/src/test/java/delft/{testfile}')

        # Run `andy` on the current assignment.
        output = os.popen('mvn andy:run').read()
        score = int(re.search('Final grade: [0-9]+', output).group().split(' ')[2])

        # Print the score for the assignment.
        print(f'{assignment_dir.split("/")[-2]}/{assignment_dir.split("/")[-1]}: {score}/100')

        # Update the `pipeline_failed` variable.
        if score != 100:
            print(output)
            pipeline_failed = True

if pipeline_failed:
    sys.exit('Some assignments do not have 100/100.')
